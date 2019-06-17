package com.xinhuo.demo.service;

import cn.com.pcauto.shangjia.base.dto.RequestMsg;
import cn.com.pcauto.shangjia.base.exception.ErrorCodeException;
import cn.com.pcauto.shangjia.preview.entity.Role;
import cn.com.pcauto.shangjia.preview.mapper.RoleMapper;
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
public class RoleService extends BaseService<RoleMapper,Role> {


    /**
     * 新增或修改Role
     * @return
     */
    public boolean saveRole(RequestMsg requestMsg){
        JSONObject authInfo = requestMsg.getAuthInfo();
        JSONObject data = requestMsg.getData();
        int id = data.getIntValue("id");
		
		Role role = new Role();
		role.setName(data.getString("name"));
		role.setDes(data.getString("des"));

        if(id > 0){//修改
            Role preRole = this.getById(id);
            if(role == null){
                throw new ErrorCodeException(-1,
                        "save-role-null","修改的数据不存在");
            }
            //将role不为空的copy到preRole,更新role
            BeanUtils.copyProperties(role,preRole);
            boolean result = this.updateById(preRole);//更新
            return result;
        }else{
            //新增
            return this.save(role);//新增
        }
    }


}
