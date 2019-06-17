package com.xinhuo.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;

/**
 * 描述: 
 * author: 新林
 * date: 2019-06-17
 */
@TableName("qrole")
public class Role implements Serializable {


    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value="id", type= IdType.AUTO)
    private int id;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String des;

    public int getId() {
	return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
	return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getDes() {
	return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}