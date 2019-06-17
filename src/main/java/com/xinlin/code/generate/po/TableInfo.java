package com.xinlin.code.generate.po;

import java.util.*;

/**
 * author 张新林
 * 时间 2019/6/11 23:50
 * 描述
 */
public class TableInfo {
    private String name;
    private String comment="";
    private List<TableField> fields;



    public TableInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<TableField> getFields() {
        return fields;
    }

    public void setFields(List<TableField> fields) {
        this.fields = fields;
    }
}
