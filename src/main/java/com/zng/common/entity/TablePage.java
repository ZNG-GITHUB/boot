package com.zng.common.entity;

/**
 * Created by John.Zhang on 2018/4/28.
 */
public class TablePage {

    private Integer toPage;

    private Integer pageSize;

    public Integer getToPage() {
        return toPage;
    }

    public void setToPage(Integer toPage) {
        this.toPage = toPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
