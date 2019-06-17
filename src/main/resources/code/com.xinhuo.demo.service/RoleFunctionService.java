package com.xinhuo.demo.service;

import cn.com.pcauto.shangjia.base.dto.RequestMsg;
import cn.com.pcauto.shangjia.base.exception.ErrorCodeException;
import cn.com.pcauto.shangjia.preview.entity.RoleFunction;
import cn.com.pcauto.shangjia.preview.mapper.RoleFunctionMapper;
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
public class RoleFunctionService extends BaseService<RoleFunctionMapper,RoleFunction> {


    /**
     * 新增或修改RoleFunction
     * @return
     */
    public boolean saveRoleFunction(RequestMsg requestMsg){
        JSONObject authInfo = requestMsg.getAuthInfo();
        JSONObject data = requestMsg.getData();
        int id = data.getIntValue("id");
		
		RoleFunction roleFunction = new RoleFunction();
		roleFunction.setRoleId(data.getString("roleId"));
		roleFunction.setFunctionId(data.getString("functionId"));

        if(id > 0){//修改
            RoleFunction preRoleFunction = this.getById(id);
            if(roleFunction == null){
                throw new ErrorCodeException(-1,
                        "save-roleFunction-null","修改的数据不存在");
            }
            //将roleFunction不为空的copy到preRoleFunction,更新roleFunction
            BeanUtils.copyProperties(roleFunction,preRoleFunction);
            boolean result = this.updateById(preRoleFunction);//更新
            return result;
        }else{
            //新增
            return this.save(roleFunction);//新增
        }
    }


}
