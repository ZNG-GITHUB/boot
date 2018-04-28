package com.zng.common.entity;

import java.util.List;

/**
 * Created by John.Zhang on 2018/4/28.
 */
public class TableCondition {

    public static final String EQUAL = "=";

    public static final String LIKE = "like";

    public static final String IN = "in";

    public static final String NOT_IN = "not_in";

    private String attr;

    private String op;

    private String value;

    private List<String> values;

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}
