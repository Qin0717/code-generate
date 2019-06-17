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
@TableName("quser_role")
public class UserRole implements Serializable {


    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value="id", type= IdType.AUTO)
    private int id;

    /**
     * 
     */
    private int userId;

    /**
     * 
     */
    private int roleId;

    public int getId() {
	return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getUserId() {
	return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getRoleId() {
	return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}