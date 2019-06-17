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
@TableName("quser")
public class User implements Serializable {


    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value="id", type= IdType.AUTO)
    private int id;

    /**
     * 账号
     */
    private String account;

    /**
     * 名称
     */
    private String name;

    /**
     * 部门
     */
    private String department;

    /**
     * 登录时间
     */
    private Date loginTime;

    /**
     * 登录IP
     */
    private String loginIp;

    /**
     * 
     */
    private String st;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public int getId() {
	return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getAccount() {
	return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
    public String getName() {
	return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getDepartment() {
	return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
    public Date getLoginTime() {
	return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
    public String getLoginIp() {
	return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }
    public String getSt() {
	return st;
    }

    public void setSt(String st) {
        this.st = st;
    }
    public Date getCreateTime() {
	return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getUpdateTime() {
	return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}