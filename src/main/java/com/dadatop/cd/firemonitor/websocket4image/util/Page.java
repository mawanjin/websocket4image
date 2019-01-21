package com.dadatop.cd.firemonitor.websocket4image.util;

import java.util.List;

public class Page<E> {
    private int total;
    private int pageSize=10;
    private int pageNo=1;
    private int totalPage;
    private boolean isFirst;
    private boolean isLast;
    private List<E> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getTotalPage() {
        if(total%pageSize==0){
            return total/pageSize;
        }else {
            return total/pageSize+1;
        }
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public boolean isFirst() {
        if(pageNo<=1)return true;
        return false;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }

    public boolean isLast() {
        if(pageNo>=getTotalPage())return true;
        return false;
    }

    public void setLast(boolean last) {
        isLast = last;
    }

    public List<E> getData() {
        return data;
    }

    public void setData(List<E> data) {
        this.data = data;
    }
}
