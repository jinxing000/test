package com.ef.golf.core.structure.base;

import com.ef.golf.core.structure.pageModel.Page;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * <b>function:</b> BaseSqlMapper继承SqlMapper，对Mapper进行接口封装，提供常用的增删改查组件；
 * 也可以对该接口进行扩展和封装
 */
public interface BaseSqlMapper<T> extends SqlMapper {
    
    public void insert(T entity) throws DataAccessException;
    
    public void updateByPrimaryKeySelective(T entity) throws DataAccessException;
    
    public void deleteByPrimaryKey(Long id) throws DataAccessException;
    
    //public T getAdmin(String name) throws DataAccessException;
    
    public T query(T entity) throws DataAccessException;
    
    public List<T> queryList(T entity) throws DataAccessException;
    //分页查询
    List<T> queryListPage(Page page);
}
