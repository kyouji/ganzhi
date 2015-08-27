package com.ynyes.lixue.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.lixue.entity.TdArticle;

/**
 * TdArticle 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdArticleRepo extends
		PagingAndSortingRepository<TdArticle, Long>,
		JpaSpecificationExecutor<TdArticle> 
{
    // 通过父类型查找
    List<TdArticle> findByMenuIdOrderBySortIdAsc(Long menuId);
    Page<TdArticle> findByMenuIdOrderBySortIdAsc(Long menuId, Pageable page);
    
    Page<TdArticle> findByMenuIdAndStatusIdOrderByIdDesc(Long menuId, Long statusId, Pageable page);
    
    Page<TdArticle> findByMenuIdAndStatusIdOrderByIdAsc(Long menuId, Long statusId, Pageable page);//顺序查找 zhangji
    
    Page<TdArticle> findByMenuIdAndCategoryIdOrderBySortIdAsc(Long menuId, Long catId, Pageable page);
    
    Page<TdArticle> findByMenuIdAndCategoryIdAndStatusIdOrderByIdDesc(Long menuId, Long catId, Long statusId, Pageable page);
    
    //顺序排列 zhangji
    Page<TdArticle> findByMenuIdAndCategoryIdAndStatusIdOrderByIdAsc(Long menuId, Long catId, Long statusId, Pageable page);
    
    Page<TdArticle> findByChannelIdAndCategoryIdOrderBySortIdAsc(Long channeldId, Long catId, Pageable page);
    
    List<TdArticle> findByMenuIdAndCategoryIdAndStatusIdOrderByIdDesc(Long menuId, Long catId, Long statusId);
    
    List<TdArticle> findByCategoryIdAndChannelIdAndParamIsSearchableTrueOrderBySortIdAsc(Long catId, Long channelId);
    
    List<TdArticle> findByCategoryIdOrderBySortIdAsc(Long catId);
    Page<TdArticle> findByCategoryIdOrderBySortIdAsc(Long catId, Pageable page);
    
    List<TdArticle> findByChannelIdAndCategoryIdOrderBySortIdAsc(Long channeldId, Long catId);
    Page<TdArticle> findByChannelIdOrderBySortIdAsc(Long channeldId, Pageable page);
    
    //搜索课程 zhangji
    Page<TdArticle> findByTitleContainingIgnoreCaseAndStatusId(
    		String keywords,Long statusId, Pageable page);
    
    int countByCategoryId(Long catId);
}
