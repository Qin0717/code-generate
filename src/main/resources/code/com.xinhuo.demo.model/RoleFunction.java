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
@TableName("qrole_function")
public class RoleFunction implements Serializable {


    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value="id", type= IdType.AUTO)
    private int id;

    /**
     * 
     */
    private int roleId;

    /**
     * 
     */
    private int functionId;

    public int getId() {
	return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getRoleId() {
	return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    public int getFunctionId() {
	return functionId;
    }

    public void setFunctionId(int functionId) {
        this.functionId = functionId;
    }
}