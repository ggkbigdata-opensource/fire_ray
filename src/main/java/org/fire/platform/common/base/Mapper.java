package org.fire.platform.common.base;

import java.util.List;
import java.util.Map;

public interface Mapper<T> {
	
	/**
	 * 根据主键删除记录
	 * 
	 * @param param
	 * 
	 */
    int deleteByPrimaryKey(Long id);
    
    /**
     * 根据条件删除
     * @param param
     * @return
     */
    int deleteByParam(Map<String, Object> map);

    /**
	 * 插入记录
	 * 
	 * @param param
	 * 实体对象
	 */
    int insert(T bean);

    
    /**
   	 * 根据主键查询
   	 * @param param
   	 * 实体对象
   	 */
    T selectByPrimaryKey(Long id);

    /**
     * 更新记录
     * @param record
     * @return
     */
    int updateByPrimaryKey(T bean);
    
    /**
     * 查询全部
     * @return
     */
    List<T> selectAll();
    
    /**
     * 根据查询条件查询
     * @param param
     * @return
     */
    List<T> selectByMap(Map<String, Object> map);
    
    
}
