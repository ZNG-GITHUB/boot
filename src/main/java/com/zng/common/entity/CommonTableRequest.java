package com.zng.common.entity;


import java.util.HashMap;
import java.util.List;

/**
 * Created by John.Zhang on 2018/4/28.
 */
public class CommonTableRequest {

    private List<TableCondition> conditions;

    private List<TableSort> sorts;

    private TablePage page;

    public List<TableCondition> getConditions() {
        return conditions;
    }

    public void setConditions(List<TableCondition> conditions) {
        this.conditions = conditions;
    }

    public List<TableSort> getSorts() {
        return sorts;
    }

    public void setSorts(List<TableSort> sorts) {
        this.sorts = sorts;
    }

    public TablePage getPage() {
        return page;
    }

    public void setPage(TablePage page) {
        this.page = page;
    }
}
