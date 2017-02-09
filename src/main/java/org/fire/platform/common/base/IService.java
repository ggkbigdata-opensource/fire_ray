/*
 * <p>Title:ICrudService.java </p>
 * <p>Company: Scho</p>
 * @author wangxiaobing
 * @version 1.0
 * @time: 2015年6月4日
 */
package org.fire.platform.common.base;

import java.util.List;
import java.util.Map;

import org.fire.platform.common.page.PageInfo;

/**
 * @author wangxiaobing
 */
public interface IService<T> {

	/**
	 * 新增
	 * @param record
	 * @return
	 */
	int insert(T bean);
	
	
	/**
	 * 修改
	 * @param record
	 * @return
	 */
	int update(T bean);
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	int delete(Long id);
	
	/**
	 * 根据ID获取
	 * @param id
	 * @return
	 */
	T get(Long id);
	
	/**
	 * 查询全部
	 * @return
	 */
	List<T> queryAll();
	
	/**
	 * 分页获取
	 * @param page
	 * @param size
	 * @return
	 */
	PageInfo<T> queryPage(int pageNo,int pageSize);
	
	/**
	 * 
	 * @param map
	 * @return
	 */
	List<T> queryByMap(Map<String,Object> map);
	
	/**
	 * 
	 * @param page
	 * @param size
	 * @param map
	 * @return
	 */
	PageInfo<T> queryPageByMap(Map<String,Object> map,int pageNo,int pageSize);
	
	

}
