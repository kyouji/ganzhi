package com.ynyes.ganzhi.controller.front;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.neo4j.cypher.internal.compiler.v2_1.perty.printToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.ganzhi.entity.TdArticle;
import com.ynyes.ganzhi.entity.TdArticleCategory;
import com.ynyes.ganzhi.entity.TdDemand;
import com.ynyes.ganzhi.entity.TdNavigationMenu;
import com.ynyes.ganzhi.service.TdArticleCategoryService;
import com.ynyes.ganzhi.service.TdArticleService;
import com.ynyes.ganzhi.service.TdCommonService;
import com.ynyes.ganzhi.service.TdDemandService;
import com.ynyes.ganzhi.service.TdNavigationMenuService;
import com.ynyes.ganzhi.service.TdUserRecentVisitService;
import com.ynyes.ganzhi.util.ClientConstant;

/**
 * 
 * 资讯
 *
 */
@Controller
@RequestMapping("/info")
public class TdInfoController {
	@Autowired 
	private TdArticleService tdArticleService;
	
	@Autowired 
    private TdArticleCategoryService tdArticleCategoryService;
	
	@Autowired 
    private TdNavigationMenuService tdNavigationMenuService;
	
	@Autowired
    private TdCommonService tdCommonService;
	
	@Autowired
    private TdUserRecentVisitService tdUserRecentVisitService;
	
	@Autowired
	private TdDemandService tdDemandService;
    
	@RequestMapping("/list/{mid}")
    public String infoList(@PathVariable Long mid, 
                            Long catId, 
    			    		String __EVENTTARGET,
    			            String __EVENTARGUMENT,
                            Integer page, 
                            ModelMap map,
                            HttpServletRequest req){	
		
	    tdCommonService.setHeader(map, req);   
	    
	    //翻页 zhangji
	    if (null != __EVENTTARGET)
        {
            if (__EVENTTARGET.equalsIgnoreCase("btnPage"))
            {
                if (null != __EVENTARGUMENT)
                {
                    page = Integer.parseInt(__EVENTARGUMENT);
                } 
            }
            
        }
	    /*翻页 end*/
	    
        if (null == page)
        {
            page = 0;
        }
	    
	    if (null == mid)
	    {
	        return "/client/error_404";
	    }	   
	    
	    TdNavigationMenu menu = tdNavigationMenuService.findOne(mid);
	    
	    map.addAttribute("menu_name", menu.getTitle());
	    map.addAttribute("menu_id", menu.getId()); //菜单id zhangji
	    map.addAttribute("menu_sub_name", menu.getName());//英文名称 zhangji
	    
	    List<TdArticleCategory> catList = tdArticleCategoryService.findByMenuId(mid);
	    
	    if (null !=catList && catList.size() > 0)
	    {
	        if (null == catId || 0 == catId)
	        {
            //    catId = catList.get(0).getId();   //.get(0)表示 取catList表的第0个 zhangji
	        	
	        	//园区概况设为12条信息一页 zhangji
	        	if(menu.getTitle().equals("园区概况"))
	        	{
	        		map.addAttribute("info_page", tdArticleService.findByMenuIdAndIsEnableOrderByIdDesc(mid, page,  ClientConstant.coursePageSize));  
	        	}
	        	else if(menu.getTitle().equals("优惠政策"))
	        	{
	        		map.addAttribute("info_page", tdArticleService.findByMenuIdAndIsEnableOrderByIdDesc(mid, page,  20));  
	        	}
	        	else
	        	{	
	        	    	map.addAttribute("info_page", tdArticleService.findByMenuIdAndIsEnableOrderByIdDesc(mid, page, ClientConstant.pageSize));   //menu下所有项
	        	}
	        }
	        else
	        {	  
	        	if(menu.getTitle().equals("园区概况")||menu.getTitle().equals("服务体系")||menu.getTitle().equals("创业导师"))
	        	{
	        		map.addAttribute("info_page", tdArticleService.findByMenuIdAndCategoryId(mid, catId, page,  ClientConstant.coursePageSize));
	        	}
	        	else if(menu.getTitle().equals("优惠政策"))
	        	{
	        		map.addAttribute("info_page", tdArticleService.findByMenuIdAndCategoryIdAndIsEnableOrderByIdDesc(mid, catId, page,  20));  
	        	}
	        	else
	        	{
	        		map.addAttribute("info_page", tdArticleService.findByMenuIdAndCategoryIdAndIsEnableOrderByIdDesc(mid, catId, page, ClientConstant.pageSize));	    
	        	}
	        }	
	    }
	    
        if(13 == mid && null == catId)
        {
        	catId = catList.get(0).getId();   //.get(0)表示 取catList表的第0个 zhangji
        }
        	
	    
	    map.addAttribute("info_name",tdArticleCategoryService.findOne(catId) );   //找出栏目名称 zhangji
	    map.addAttribute("catId", catId);
	    map.addAttribute("mid", mid);
	    map.addAttribute("info_category_list", catList); //栏目的列表 zhangji
	    map.addAttribute("latest_info_page", tdArticleService.findByMenuIdAndIsEnableOrderByIdDesc(mid, page, ClientConstant.pageSize));
	    
        return "/client/info_list";
    }
	
	@RequestMapping("/list/content/{id}")
    public String content(@PathVariable Long id, Long mid, ModelMap map, HttpServletRequest req){
        
	    tdCommonService.setHeader(map, req);
	    
        if (null == id || null == mid)
        {
            return "/client/error_404";
        }             
        
        TdNavigationMenu menu = tdNavigationMenuService.findOne(mid);
        
        map.addAttribute("menu_name", menu.getTitle());      
	    map.addAttribute("menu_id", menu.getId()); //菜单id zhangji
	    map.addAttribute("menu_sub_name", menu.getName());//英文名称 zhangji
        
        List<TdArticleCategory> catList = tdArticleCategoryService.findByMenuId(mid);
      
        map.addAttribute("info_category_list", catList);
        map.addAttribute("mid", mid);
        
        TdArticle tdArticle = tdArticleService.findOne(id);
        //浏览量 zhangji
        Long viewCount = tdArticle.getViewCount();
        tdArticle.setViewCount(viewCount+1);
        tdArticleService.save(tdArticle);
        //找出栏目名称 zhangji
        Long catId = tdArticle.getCategoryId();
        map.addAttribute("info_name",tdArticleCategoryService.findOne(catId) );
        map.addAttribute("catId", catId);
        
        if (null != tdArticle)
        {
        	map.addAttribute("id", id);
            map.addAttribute("info", tdArticle);
            map.addAttribute("prev_info", tdArticleService.findPrevOne(id, tdArticle.getCategoryId(), tdArticle.getMenuId()));
            map.addAttribute("next_info", tdArticleService.findNextOne(id, tdArticle.getCategoryId(), tdArticle.getMenuId()));
        }
        
        // 最近添加
        map.addAttribute("latest_info_page", tdArticleService.findByMenuIdAndIsEnableOrderByIdDesc(mid, 0, ClientConstant.pageSize));
        
        return "/client/info_list_content";
    }
	
	@RequestMapping("/list/content/cat/{catId}")
    public String contentcat(@PathVariable Long catId, Long mid, ModelMap map, HttpServletRequest req){
        
	    tdCommonService.setHeader(map, req);
	    
        if (null == catId || null == mid)
        {
            return "/client/error_404";
        }             
        Long id = tdArticleService.findByCategoryId(catId).get(0).getId();
        
        TdNavigationMenu menu = tdNavigationMenuService.findOne(mid);
        
        map.addAttribute("menu_name", menu.getTitle());      
	    map.addAttribute("menu_id", menu.getId()); //菜单id zhangji
	    map.addAttribute("menu_sub_name", menu.getName());//英文名称 zhangji
        
        List<TdArticleCategory> catList = tdArticleCategoryService.findByMenuId(mid);
      
        map.addAttribute("info_category_list", catList);
        map.addAttribute("mid", mid);
        
        TdArticle tdArticle = tdArticleService.findOne(id);
        //浏览量 zhangji
        Long viewCount = tdArticle.getViewCount();
        tdArticle.setViewCount(viewCount+1);
        tdArticleService.save(tdArticle);
        //找出栏目名称 zhangji
        map.addAttribute("info_name",tdArticleCategoryService.findOne(catId) );
        map.addAttribute("catId", catId);
        
        if (null != tdArticle)
        {
        	map.addAttribute("id", id);
            map.addAttribute("info", tdArticle);
            map.addAttribute("prev_info", tdArticleService.findPrevOne(id, tdArticle.getCategoryId(), tdArticle.getMenuId()));
            map.addAttribute("next_info", tdArticleService.findNextOne(id, tdArticle.getCategoryId(), tdArticle.getMenuId()));
        }
        
        // 最近添加
        map.addAttribute("latest_info_page", tdArticleService.findByMenuIdAndIsEnableOrderByIdDesc(mid, 0, ClientConstant.pageSize));
        
        return "/client/info_list_content";
    }
	
	/**
	 * ajax列表选择
	 * @author Zhangji
	 * @param mid
	 * @param catId
	 * @param page
	 * @param map
	 * @param req
	 * @return
	 */
	@RequestMapping("/list/select")
    public String infoListSelect( Long mid, 
                            Long catId, 
    			    		String __EVENTTARGET,
    			            String __EVENTARGUMENT,
                            Integer page, 
                            ModelMap map,
                            HttpServletRequest req){
	    
	    tdCommonService.setHeader(map, req);
	    
	    //翻页 zhangji
	    if (null != __EVENTTARGET)
        {
            if (__EVENTTARGET.equalsIgnoreCase("btnPage"))
            {
                if (null != __EVENTARGUMENT)
                {
                	page = Integer.parseInt(__EVENTARGUMENT);
                }
            }
            
        }
	    /*翻页 end*/
           
	    if (null == mid)
	    {
	        return "/client/error_404";
	    }
	    
	    if (null == page)
	    {
	        page = 0;
	    }
	    
	    TdNavigationMenu menu = tdNavigationMenuService.findOne(mid);
	    
	    map.addAttribute("menu_name", menu.getTitle());
	    map.addAttribute("menu_id", menu.getId()); //菜单id zhangji
	    map.addAttribute("menu_sub_name", menu.getName());//英文名称 zhangji
	    
	    List<TdArticleCategory> catList = tdArticleCategoryService.findByMenuId(mid);
	    
	    if (null !=catList && catList.size() > 0)
	    {
	    	if (null == catId || 0 == catId)
	        {
  //            catId = catList.get(0).getId();   //.get(0)表示 取catList表的第0个 zhangji
	        	
	        	//园区概况设为12条信息一页 zhangji
	        	if(menu.getTitle().equals("园区概况")||menu.getTitle().equals("创客空间"))
	        	{
	        		map.addAttribute("info_page", tdArticleService.findByMenuId(mid, page,  ClientConstant.coursePageSize));
	        	}
	        	else if(menu.getTitle().equals("优惠政策"))
	        	{
	        		map.addAttribute("info_page", tdArticleService.findByMenuIdAndIsEnableOrderByIdDesc(mid, page,  20));  
	        	}
	        	else
	        	{	
	        		map.addAttribute("info_page", tdArticleService.findByMenuIdAndIsEnableOrderByIdDesc(mid, page, ClientConstant.pageSize));   //menu下所有项
	        	}
	        }
	        else
	        {	  
	        	if(menu.getTitle().equals("园区概况")||menu.getTitle().equals("服务体系"))
	        	{
	        		if (catId == 30||catId == 52||catId == 46)
	        		{
	        			map.addAttribute("info_page", tdArticleService.findByMenuIdAndCategoryId(mid, catId, page, ClientConstant.coursePageSize));
	        		}
	        		else
	        		{
	        			map.addAttribute("info_page", tdArticleService.findByMenuIdAndCategoryIdAndIsEnableOrderByIdDesc(mid, catId, page,  ClientConstant.coursePageSize));
	        		}
	        	}
	        	else if(menu.getTitle().equals("优惠政策"))
	        	{
	        		map.addAttribute("info_page", tdArticleService.findByMenuIdAndCategoryIdAndIsEnableOrderByIdDesc(mid,  catId, page,  20));  
	        	}
	        	else
	        	{
	        		if (catId == 59)
	        		{
	        			Long id = tdArticleService.findByCategoryId(catId).get(0).getId();
	        	        TdArticle tdArticle = tdArticleService.findOne(id);
	        	        //浏览量 zhangji
	        	        Long viewCount = tdArticle.getViewCount();
	        	        tdArticle.setViewCount(viewCount+1);
	        	        tdArticleService.save(tdArticle);
	        	        
	        	        if (null != tdArticle)
	        	        {
	        	        	map.addAttribute("id", id);
	        	        	map.addAttribute("catId", catId);
	        	        	map.addAttribute("mid", mid);
	        	         	map.addAttribute("info_name",tdArticleCategoryService.findOne(catId) );   //找出栏目名称 zhangji
	        	            map.addAttribute("info", tdArticle);
	        	            map.addAttribute("prev_info", tdArticleService.findPrevOne(id, tdArticle.getCategoryId(), tdArticle.getMenuId()));
	        	            map.addAttribute("next_info", tdArticleService.findNextOne(id, tdArticle.getCategoryId(), tdArticle.getMenuId()));
	        	        }
	        	        
	        	        return "/client/info_list_content_detail";
	        	        
	        		}
	        		else{
	        				map.addAttribute("info_page", tdArticleService.findByMenuIdAndCategoryIdAndIsEnableOrderByIdDesc(mid, catId, page, ClientConstant.pageSize));	    
	        		}
	        	}
	        }	      
	    }   
	    
	    map.addAttribute("info_name",tdArticleCategoryService.findOne(catId) );   //找出栏目名称 zhangji
	    map.addAttribute("catId", catId);
	    map.addAttribute("mid", mid);
	    map.addAttribute("info_category_list", catList); //栏目的列表 zhangji
	    map.addAttribute("latest_info_page", tdArticleService.findByMenuIdAndIsEnableOrderByIdDesc(mid, page, ClientConstant.pageSize));
	    
        return "/client/info_list_detail";
    }
	
	@RequestMapping("/list/content/select")
    public String contentSelect(Long id, Long mid, ModelMap map, HttpServletRequest req){
        
	    tdCommonService.setHeader(map, req);
	    
        if (null == id || null == mid)
        {
            return "/client/error_404";
        }             
        
        TdNavigationMenu menu = tdNavigationMenuService.findOne(mid);
        
        map.addAttribute("menu_name", menu.getTitle());      
	    map.addAttribute("menu_id", menu.getId()); //菜单id zhangji
	    map.addAttribute("menu_sub_name", menu.getName());//英文名称 zhangji
        
        List<TdArticleCategory> catList = tdArticleCategoryService.findByMenuId(mid);
      
        map.addAttribute("info_category_list", catList);
        map.addAttribute("mid", mid);
        
        TdArticle tdArticle = tdArticleService.findOne(id);
        
        //找出栏目名称 zhangji
        Long catId = tdArticle.getCategoryId();
        map.addAttribute("info_name",tdArticleCategoryService.findOne(catId) );
        map.addAttribute("catId", catId);
        
        //浏览量 zhangji
        Long viewCount = tdArticle.getViewCount();
        tdArticle.setViewCount(viewCount+1);
        tdArticleService.save(tdArticle);
        
        if (null != tdArticle)
        {
            map.addAttribute("info", tdArticle);
            map.addAttribute("prev_info", tdArticleService.findPrevOne(id, tdArticle.getCategoryId(), tdArticle.getMenuId()));
            map.addAttribute("next_info", tdArticleService.findNextOne(id, tdArticle.getCategoryId(), tdArticle.getMenuId()));
        }
        
        // 最近添加
        map.addAttribute("latest_info_page", tdArticleService.findByMenuIdAndIsEnableOrderByIdDesc(mid, 0, ClientConstant.pageSize));
        
        return "/client/info_list_content_detail";
    }
	
	/**
	 * 服务项目ajax局部刷新
	 * @author Zhangji
	 * @param mid
	 * @param catId
	 * @param page
	 * @param map
	 * @param req
	 * @return
	 */
	@RequestMapping("/entry/select")
    public String serviceList(Long id, 
                            Long mid, 
                            Integer page, 
                            ModelMap map,
                            HttpServletRequest req){
	    
		 tdCommonService.setHeader(map, req);
		    
	        if (null == id || null == mid)
	        {
	            return "/client/error_404";
	        }      

	        map.addAttribute("mid", mid);
	        
	        TdArticle tdArticle = tdArticleService.findOne(id);
	        
	        //找出栏目名称 zhangji
	        Long catId = tdArticle.getCategoryId();
	        map.addAttribute("info_name",tdArticleCategoryService.findOne(catId) );
	        
	        //列表信息 zhangji
	        map.addAttribute("info_page", tdArticleService
	                .findByMenuIdAndCategoryIdAndIsEnableOrderByIdDesc(mid,
	                        catId, 0, ClientConstant.pageSize));
	        
	        if (null != tdArticle)
	        {
	            map.addAttribute("info", tdArticle);
	            map.addAttribute("prev_info", tdArticleService.findPrevOne(id, tdArticle.getCategoryId(), tdArticle.getMenuId()));
	            map.addAttribute("next_info", tdArticleService.findNextOne(id, tdArticle.getCategoryId(), tdArticle.getMenuId()));
	        }
	        
	        // 最近添加
	        map.addAttribute("latest_info_page", tdArticleService.findByMenuIdAndIsEnableOrderByIdDesc(mid, 0, ClientConstant.pageSize));
	      
        return "/client/info_entry_content_detail";
    }
	
	/**
	 * 服务项目内容
	 * @author Zhangji
	 * @param id
	 * @param mid
	 * @param map
	 * @param req
	 * @return
	 */
	@RequestMapping("/entry/content/{id}")
    public String serviceContent(@PathVariable Long id, Long mid, ModelMap map, HttpServletRequest req){
        
	    tdCommonService.setHeader(map, req);
	    
        if (null == id || null == mid)
        {
            return "/client/error_404";
        }      
        
        TdNavigationMenu menu = tdNavigationMenuService.findOne(mid);
        
        map.addAttribute("menu_name", menu.getTitle());   
	    map.addAttribute("menu_id", menu.getId()); //菜单id zhangji
	    map.addAttribute("menu_sub_name", menu.getName());//英文名称 zhangji
        
        List<TdArticleCategory> catList = tdArticleCategoryService.findByMenuId(mid);

        map.addAttribute("info_category_list", catList);
        map.addAttribute("mid", mid);
        
        //导航栏[关于我们]跳转 zhangji
        if(-1 == id)
        {
        	 for (TdArticleCategory tdCat : catList)
             {
                 if (null != tdCat.getTitle() && tdCat.getTitle().equals("关于我们"))
                 {
                	 Long catId = tdCat.getId();
                	 List<TdArticle> profile = tdArticleService.findByCategoryId(catId);
                	 id = profile.get(0).getId();
                     break;               
                 }                     
             }
        	
        }
        //导航栏[服务项目]跳转 zhangji
        if(-2 == id)
        {
        	 for (TdArticleCategory tdCat : catList)
             {
                 if (null != tdCat.getTitle() && tdCat.getTitle().equals("服务项目"))
                 {
                	 Long catId = tdCat.getId();
                	 List<TdArticle> profile = tdArticleService.findByCategoryId(catId);
                	 id = profile.get(0).getId();
                     break;               
                 }                     
             }
        	
        }
        
        TdArticle tdArticle = tdArticleService.findOne(id);
        
        //找出栏目名称 zhangji
        Long catId = tdArticle.getCategoryId();
        map.addAttribute("info_name",tdArticleCategoryService.findOne(catId) );
        
        //列表信息 zhangji
        map.addAttribute("info_page", tdArticleService
                .findByMenuIdAndCategoryIdAndIsEnableOrderByIdAsc(mid,
                        catId, 0, ClientConstant.pageSize));
        

        if (null != tdArticle)
        {
            map.addAttribute("info", tdArticle);
            map.addAttribute("prev_info", tdArticleService.findPrevOne(id, tdArticle.getCategoryId(), tdArticle.getMenuId()));
            map.addAttribute("next_info", tdArticleService.findNextOne(id, tdArticle.getCategoryId(), tdArticle.getMenuId()));
        }
        
        // 最近添加
        map.addAttribute("latest_info_page", tdArticleService.findByMenuIdAndIsEnableOrderByIdDesc(mid, 0, ClientConstant.pageSize));
        
        return "/client/info_entry_content";
    }
	
	/**
	 * 
	 * 课程报名
	 * @param id
	 * @param mid
	 * @param map
	 * @param req
	 * @return
	 */
	@RequestMapping("/coursechoose")
	public String coursechoose( Long id,Long mid, ModelMap map, HttpServletRequest req)
	{
		TdArticle article = tdArticleService.findOne(id);
		map.addAttribute("coursetake",article.getTitle());
		map.addAttribute("courseId",id);
		map.addAttribute("courseMid",mid);
		map.addAttribute("info", article);
		
	    TdNavigationMenu menu = tdNavigationMenuService.findOne(mid);
	    
	    map.addAttribute("menu_name", menu.getTitle());
	    map.addAttribute("menu_id", menu.getId()); //菜单id zhangji
	    map.addAttribute("menu_sub_name", menu.getName());//英文名称 zhangji
		return "/client/info_list_detail";
	}
	
	@RequestMapping("/submit")
	@ResponseBody
    public Map<String, Object> setConsult(TdDemand userDemand, HttpServletRequest req)
    {
		Map<String, Object> res = new HashMap<String, Object>();
    	res.put("code", 1);
    	if (userDemand.getName() == null || userDemand.getName().equals(""))
    	{
			res.put("message", "用户名不能为空");
			return res;
		}
    	else if (userDemand.getMobile().equals(""))
    	{
			res.put("message", "手机号不能为空");
			return res;
		}
    	else if (userDemand.getContent() ==null|| userDemand.getContent().equals(""))
    	{
			res.put("message", "留言内容不能为空");
			return res;
		}
    	
    	userDemand.setTime(new Date());
    	userDemand.setStatusId(1L);     //zhangji
    	tdDemandService.save(userDemand);
    	res.put("code", 0);
    	return res;
    }
	
	@RequestMapping("/coursechoose/content/{id}")
    public String coursechooseContent(@PathVariable Long id, Long mid, ModelMap map, HttpServletRequest req){
        
	    tdCommonService.setHeader(map, req);
	    
        if (null == id || null == mid)
        {
            return "/client/error_404";
        }             
        
        TdNavigationMenu menu = tdNavigationMenuService.findOne(mid);
        
        map.addAttribute("menu_name", "园区概况");      
	    map.addAttribute("menu_id", menu.getId()); //菜单id zhangji
	    map.addAttribute("menu_sub_name", menu.getName());//英文名称 zhangji
        
	    TdArticle tdArticle = tdArticleService.findOne(id);
	    map.addAttribute("coursetake",tdArticle.getTitle());
	    map.addAttribute("courseId",id);
        //找出栏目名称 zhangji
        Long catId = tdArticle.getCategoryId();
        map.addAttribute("info_name",tdArticleCategoryService.findOne(catId) );
        
        //列表信息 zhangji
        map.addAttribute("info_page", tdArticleService
                .findByMenuIdAndCategoryIdAndIsEnableOrderByIdAsc(mid,
                        catId, 0, ClientConstant.pageSize));
        
	    
        List<TdArticleCategory> catList = tdArticleCategoryService.findByMenuId(mid);
      
        map.addAttribute("info_category_list", catList);
        map.addAttribute("mid", mid);
        
        
        //找出栏目名称 zhangji
        map.addAttribute("info_name",tdArticleCategoryService.findOne(catId) );
        
        if (null != tdArticle)
        {
            map.addAttribute("info", tdArticle);
            map.addAttribute("prev_info", tdArticleService.findPrevOne(id, tdArticle.getCategoryId(), tdArticle.getMenuId()));
            map.addAttribute("next_info", tdArticleService.findNextOne(id, tdArticle.getCategoryId(), tdArticle.getMenuId()));
        }
        
        // 最近添加
        map.addAttribute("latest_info_page", tdArticleService.findByMenuIdAndIsEnableOrderByIdDesc(mid, 0, ClientConstant.pageSize));
        
        return "/client/info_list";
    }
	@RequestMapping("/map")
    public String map(ModelMap map, HttpServletRequest req){
        
	    tdCommonService.setHeader(map, req);
	    map.addAttribute("menu_name", "交通指南");
	    map.addAttribute("menu_id", 11L); //菜单id zhangji
	    map.addAttribute("menu_sub_name", "Map");//英文名称 zhangji
	    map.addAttribute("message", "地图导航");
	    map.addAttribute("back", "退出");
	    return "/client/map";
	}
}
