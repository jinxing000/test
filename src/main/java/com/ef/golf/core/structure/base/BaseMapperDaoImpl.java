package com.ef.golf.core.structure.base;

import com.ef.golf.core.structure.pageModel.Page;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SuppressWarnings("unchecked")
public class BaseMapperDaoImpl <T> extends SqlSessionTemplate implements BaseMapperDao<T>{

	@Autowired
    public BaseMapperDaoImpl(SqlSessionFactory sqlSessionFactory) {
        super(sqlSessionFactory);
    }
    
    private Class<? extends BaseSqlMapper> mapperClass;
    
    public void setMapperClass(Class<? extends BaseSqlMapper> mapperClass) {
        this.mapperClass = mapperClass;
    }
 
    public BaseSqlMapper<T> getMapper() {
        return this.getMapper(mapperClass);
    }
    
    public boolean insert(T entity) throws Exception {
        boolean flag = false;
        try {
            this.getMapper().insert(entity);
            flag = true;
        } catch (Exception e) {
            flag = false;
            throw e;
        }
        return flag;
    }
 
    public boolean updateByPrimaryKeySelective(T entity) throws Exception {
        boolean flag = false;
        try {
            this.getMapper().updateByPrimaryKeySelective(entity);
            flag = true;
        } catch (Exception e) {
            flag = false;
            throw e;
        }
        return flag;
    }
 
    public T query(T entity) {
        return this.getMapper().query(entity);
    }
 
    public List<T> queryList() {
        return this.getMapper().queryList(null);
    }
	public List<T> queryList(T entity) {
		return this.getMapper().queryList(entity);
	}
    public boolean deleteByPrimaryKey(Long id) throws Exception {
        boolean flag = false;
        try {
            this.getMapper().deleteByPrimaryKey(id);
            flag = true;
        } catch (Exception e) {
            flag = false;
            throw e;
        }
        return flag;
    }
    public List<T> queryListPage(Page page) {
		return this.getMapper().queryListPage(page);
	}

	
	
	

}