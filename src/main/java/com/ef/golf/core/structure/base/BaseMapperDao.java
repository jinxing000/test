package com.ef.golf.core.structure.base;


import com.ef.golf.core.structure.pageModel.Page;

import java.util.List;

public interface BaseMapperDao<T> {
    
	 @SuppressWarnings("unchecked")
	    public void setMapperClass(Class<? extends BaseSqlMapper> mapperClass);
	    
	    public BaseSqlMapper<T> getMapper();
	    
	    public boolean insert(T entity) throws Exception;
	    
	    public boolean updateByPrimaryKeySelective(T entity) throws Exception;
	    
	    public boolean deleteByPrimaryKey(Long id) throws Exception;
	    
	    public T query(T entity);
	    
	    public List<T> queryList();
	    
	    public List<T> queryList(T entity);
	    List<T> queryListPage(Page page);

		
	    
}
