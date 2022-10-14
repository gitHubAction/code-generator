package com.longfor.codegenerator.core;

import java.io.Serializable;
import java.util.List;

public class PageInfo<T> implements Serializable{
    private List<T> list;    //保存页面数据
    private int totalNum;    //查询到的总记录数
    private int currentPage;       //用户当前看的页数
    private int pageSize;        //每页多少条显示数据
    private int totalPages;        //总页数
    private int previousPage;    //上一页
    private int nextPage;        //下一页


    public static PageInfo startPage(int currentPage, int pageSize){
        return new PageInfo(currentPage, pageSize);
    }

    public PageInfo(int currentPage, int pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public void setList(List list) {
        this.list = list;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List getList() {
        return list;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalPages() {
        if (this.totalNum % this.pageSize == 0) {
            this.totalPages = this.totalNum / this.pageSize;
        } else {
            this.totalPages = this.totalNum / this.pageSize + 1;
        }
        return this.totalPages;
    }

    public int getPreviousPage() {
        if (currentPage == 1) {
            previousPage = 1;
        } else {
            previousPage = currentPage - 1;
        }
        return previousPage;
    }

    public int getNextPage() {
        if (currentPage == totalPages) {
            nextPage = totalPages;
        } else {
            nextPage = currentPage + 1;
        }
        return nextPage;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "list=" + list +
                ", totalNum=" + totalNum +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", totalPages=" + totalPages +
                ", previousPage=" + previousPage +
                ", nextPage=" + nextPage +
                '}';
    }
}
