package com.bookmanager.pojo;

import java.util.List;

/**
 * @Classname QueryObject
 * @Description TODO
 * @Date 2022/6/8 16:43
 * @Created by 晨曦
 */
public class QueryObject<T> {
    private Long total;
    private Long pageIndex;
    private List<T> objects;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Long pageIndex) {
        this.pageIndex = pageIndex;
    }

    public List<T> getObjects() {
        return objects;
    }

    public void setObjects(List<T> objects) {
        this.objects = objects;
    }
}
