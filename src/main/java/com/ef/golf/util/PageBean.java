package com.ef.golf.util;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

public class PageBean<T> implements Serializable{

        private int pageNo=1;// 当前页
        private int pageSize=5;// 每页显示的条数
        private int totalPage;// 总页数
        private int totalCount;// 总条数
        private List<T> resultList;//存放数据集合
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String subjectName;

    public List<T> getResultList() {
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }

    public int getPageNo() {
            return pageNo ;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo ;
       }

        public int getPageSize() {
           return pageSize ;
         }

        public void setPageSize(int pageSize) {
             this.pageSize = pageSize ;
        }

        public int getTotalPage() {
         return (totalCount + pageSize - 1) / pageSize ;
    }

        public void setTotalCount(int totalCount) {
         this.totalCount = totalCount ;
    }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getTotalCount() {
            return totalCount;
        }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
