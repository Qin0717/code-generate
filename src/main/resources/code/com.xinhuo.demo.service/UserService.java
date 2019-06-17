package com.xinhuo.demo.service;

import cn.com.pcauto.shangjia.base.dto.RequestMsg;
import cn.com.pcauto.shangjia.base.exception.ErrorCodeException;
import cn.com.pcauto.shangjia.preview.entity.User;
import cn.com.pcauto.shangjia.preview.mapper.UserMapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 描述: 
 * author: 新林
 * date: 2019-06-17
 */
@Service
public class UserService extends BaseService<UserMapper,User> {


    /**
     * 新增或修改User
     * @return
     */
    public boolean saveUser(RequestMsg requestMsg){
        JSONObject authInfo = requestMsg.getAuthInfo();
        JSONObject data = requestMsg.getData();
        int id = data.getIntValue("id");
		
		User user = new User();
		user.setAccount(data.getString("account"));
		user.setName(data.getString("name"));
		user.setDepartment(data.getString("department"));
		user.setLoginTime(data.getString("loginTime"));
		user.setLoginIp(data.getString("loginIp"));
		user.setSt(data.getString("st"));
		user.setCreateTime(data.getString("createTime"));
		user.setUpdateTime(data.getString("updateTime"));

        if(id > 0){//修改
            User preUser = this.getById(id);
            if(user == null){
                throw new ErrorCodeException(-1,
                        "save-user-null","修改的数据不存在");
            }
            //将user不为空的copy到preUser,更新user
            BeanUtils.copyProperties(user,preUser);
            boolean result = this.updateById(preUser);//更新
            return result;
        }else{
            //新增
            return this.save(user);//新增
        }
    }


}
