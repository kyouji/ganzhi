package com.ynyes.lixue.controller.front;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.lixue.entity.TdDemand;
import com.ynyes.lixue.entity.TdNavigationMenu;
import com.ynyes.lixue.entity.TdUserConsult;
import com.ynyes.lixue.service.TdCommonService;
import com.ynyes.lixue.service.TdNavigationMenuService;
import com.ynyes.lixue.service.TdUserConsultService;

@Controller
public class TdCooperationController {
	
	
	@Autowired
    private TdCommonService tdCommonService;
	
	@Autowired
	private TdUserConsultService tdUserConsultService;
	
	@Autowired
	private TdNavigationMenuService tdNavigationMenuService;
	
	@RequestMapping("/cooperation")
	public String cooperation(HttpServletRequest req,ModelMap map,Integer page)
	{
		tdCommonService.setHeader(map, req);
		Long mid = 12L;
		TdNavigationMenu menu = tdNavigationMenuService.findOne(mid);
	    
	    map.addAttribute("menu_name", menu.getTitle());
	    map.addAttribute("menu_id", menu.getId()); //菜单id zhangji
	    map.addAttribute("menu_sub_name", menu.getName());//英文名称 zhangji
		
		
	    if (page == null)
        {
        	Page<TdUserConsult> userConsult =  tdUserConsultService.findByStatusIdOrderByIdDesc(1L, 0, 6);
            if (userConsult != null)
            {
    			map.addAttribute("user_consult_page",userConsult);
    		}        
            return "/client/cooperation";
		}
        Page<TdUserConsult> userConsult =  tdUserConsultService.findByStatusIdOrderByIdDesc(1L, page, 6);
        if (userConsult != null)
        {
			map.addAttribute("user_consult_page",userConsult);
		}
		return "/client/cooperation";
	}
	
	
	
	@RequestMapping("/cooperation/submit")
	@ResponseBody
    public Map<String, Object> setConsult(TdUserConsult userConsult, HttpServletRequest req)
    {
		Map<String, Object> res = new HashMap<String, Object>();
    	res.put("code", 1);
    	if (userConsult.getUsername() == null || userConsult.getUsername().equals(""))
    	{
			res.put("message", "用户名不能为空");
			return res;
		}
    	else if (userConsult.getMobile() == null ||userConsult.getMobile().equals(""))
    	{
			res.put("message", "手机号不能为空");
			return res;
		}
    	else if (userConsult.getContent() ==null|| userConsult.getContent().equals(""))
    	{
			res.put("message", "留言内容不能为空");
			return res;
		}
    	
    	userConsult.setCommentTime(new Date());
    	userConsult.setStatusId(0L);
    	tdUserConsultService.save(userConsult);
    	res.put("code", 0);
    	return res;
    }
}