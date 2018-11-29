package com.ef.golf.util;

import java.util.ArrayList;
import java.util.List;

/**
 * com.ef.golf.util
 * Administrator
 * 2018/10/16 14:06
 */
public class Pager<T> {
    /**
     * 用于分页的工具类
     */
        private List<T> list = new ArrayList<T>(); //对象记录结果集
        private int total = 0; // 总记录数
        private int pageSize = 10; // 每页显示记录数
        private int pageCount = 1; // 总页数
        private int pageNumber = 1; // 当前页
        private int first = 1; // 标示导航栏的首页
        private int last; // 标示导航栏的末页  即总页数

        private boolean isFirstPage=false; // 是否为第一页
        private boolean isLastPage=false; // 是否为最后一页
        private boolean hasPrevPage=false; // 是否有前一页
        private boolean hasNextPage=false; // 是否有下一页
        private int prev; // 标示导航栏的上一页
        private int next; // 标示导航栏的下一页

        private int navPages=6; // 导航页码数
        private int[] navPagerNumbers; // 所有导航页号

        private String pageUrl = "";

        public Pager() {
        }

        public Pager(int total, int pageNumber) {
            init(total, pageNumber, pageSize);
        }

        public Pager(int total, int pageNumber, int pageSize) {
            init(total, pageNumber, pageSize);
        }

        public Pager(int total, int pageNumber, int pageSize, String pageUrl) {
            init(total, pageNumber, pageSize);
            this.pageUrl = pageUrl;
        }

        public Pager(int total, int pageNumber, int pageSize, int navPages) {
            this.navPages = navPages;
            init(total, pageNumber, pageSize);
        }

        private void init(int total, int pageNumber, int pageSize){
            //设置基本参数
            this.total = total;
            this.pageSize = pageSize;
            this.pageCount = (this.total-1)/this.pageSize+1;

            //根据输入可能错误的当前号码进行自动纠正
            if(pageNumber<1){
                this.pageNumber=1;
            }else if(pageNumber>this.pageCount){
                this.pageNumber=this.pageCount;
            }else{
                this.pageNumber=pageNumber;
            }

            //基本参数设定之后进行导航页面的计算
            calcNavPagerNumbers();

            this.last = this.pageCount;
            this.prev = Math.max(this.first, this.pageNumber - 1);
            this.next = Math.min(this.last, this.pageNumber + 1);

            //以及页面边界的判定
            judgePageBoudary();
        }

        /**
         * 计算导航页
         */
        private void calcNavPagerNumbers(){
            //当总页数小于或等于导航页码数时
            if(pageCount<=navPages){
                navPagerNumbers=new int[pageCount];
                for(int i=0;i<pageCount;i++){
                    navPagerNumbers[i]=i+1;
                }
            }else{ //当总页数大于导航页码数时
                navPagerNumbers=new int[navPages];
                int startNum=pageNumber-navPages/2;
                int endNum=pageNumber+navPages/2;

                if(startNum<1){
                    startNum=1;
                    //(最前navPageCount页
                    for(int i=0;i<navPages;i++){
                        navPagerNumbers[i]=startNum++;
                    }
                }else if(endNum>pageCount){
                    endNum=pageCount;
                    //最后navPageCount页
                    for(int i=navPages-1;i>=0;i--){
                        navPagerNumbers[i]=endNum--;
                    }
                }else{
                    //所有中间页
                    for(int i=0;i<navPages;i++){
                        navPagerNumbers[i]=startNum++;
                    }
                }
            }
        }

        /**
         * 判定页面边界
         */
        private void judgePageBoudary(){
            isFirstPage = pageNumber == 1;
            isLastPage = pageNumber == pageCount && pageNumber!=1;
            hasPrevPage = pageNumber!=1;
            hasNextPage = pageNumber!=pageCount;
        }

        public static int getFirstResult(int pageNumber, int pageSize){
            int firstResult = (pageNumber - 1) * pageSize;
            return firstResult < 0?0:firstResult;
        }


        public void setList(List<T> list) {
            this.list = list;
        }

        /**
         * 得到当前页的内容
         * @return {List}
         */
        public List<T> getList() {
            return list;
        }

        /**
         * 得到记录总数
         * @return {int}
         */
        public int getTotal() {
            return total;
        }

        /**
         * 得到每页显示多少条记录
         * @return {int}
         */
        public int getPageSize() {
            return pageSize;
        }

        /**
         * 得到页面总数
         * @return {int}
         */
        public int getPageCount() {
            return pageCount;
        }

        /**
         * 得到当前页号
         * @return {int}
         */
        public int getPageNumber() {
            return pageNumber;
        }


        /**
         * 得到所有导航页号
         * @return {int[]}
         */
        public int[] getNavPagerNumbers() {
            return navPagerNumbers;
        }

        public boolean isFirstPage() {
            return isFirstPage;
        }

        public boolean isLastPage() {
            return isLastPage;
        }

        public boolean isHasPrevPage() {
            return hasPrevPage;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public String getPageUrl() {
            return pageUrl;
        }

        public void setPageUrl(String pageUrl) {
            this.pageUrl = pageUrl;
        }

        public int getLast() {
            return last;
        }

        public int getPrev() {
            return prev;
        }

        public int getNext() {
            return next;
        }

        public String toString(){
            String str=new String();
            str= "[" +
                    "total="+total+
                    ",pageCount="+pageCount+
                    ",pageNumber="+pageNumber+
                    ",pageSize="+pageSize+
                    //",navPages="+navPages+
                    ",isFirstPage="+isFirstPage+
                    ",isLastPage="+isLastPage+
                    ",hasPrevPage="+hasPrevPage+
                    ",hasNextPage="+hasNextPage+
                    ",navPagerNumbers=";
            int len=navPagerNumbers.length;
            if(len>0)str+=(navPagerNumbers[0]);
            for(int i=1;i<len;i++){
                str+=(" "+navPagerNumbers[i]);
            }
            //sb+=",list="+list;
            str+="]";
            return str;
        }
    }

