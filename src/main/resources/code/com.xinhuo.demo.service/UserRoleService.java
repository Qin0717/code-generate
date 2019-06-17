package com.xinhuo.demo.service;

import cn.com.pcauto.shangjia.base.dto.RequestMsg;
import cn.com.pcauto.shangjia.base.exception.ErrorCodeException;
import cn.com.pcauto.shangjia.preview.entity.UserRole;
import cn.com.pcauto.shangjia.preview.mapper.UserRoleMapper;
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
public class UserRoleService extends BaseService<UserRoleMapper,UserRole> {


    /**
     * 新增或修改UserRole
     * @return
     */
    public boolean saveUserRole(RequestMsg requestMsg){
        JSONObject authInfo = requestMsg.getAuthInfo();
        JSONObject data = requestMsg.getData();
        int id = data.getIntValue("id");
		
		UserRole userRole = new UserRole();
		userRole.setUserId(data.getString("userId"));
		userRole.setRoleId(data.getString("roleId"));

        if(id > 0){//修改
            UserRole preUserRole = this.getById(id);
            if(userRole == null){
                throw new ErrorCodeException(-1,
                        "save-userRole-null","修改的数据不存在");
            }
            //将userRole不为空的copy到preUserRole,更新userRole
            BeanUtils.copyProperties(userRole,preUserRole);
            boolean result = this.updateById(preUserRole);//更新
            return result;
        }else{
            //新增
            return this.save(userRole);//新增
        }
    }


}
