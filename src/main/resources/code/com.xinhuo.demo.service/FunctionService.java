package com.xinhuo.demo.service;

import cn.com.pcauto.shangjia.base.dto.RequestMsg;
import cn.com.pcauto.shangjia.base.exception.ErrorCodeException;
import cn.com.pcauto.shangjia.preview.entity.Function;
import cn.com.pcauto.shangjia.preview.mapper.FunctionMapper;
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
public class FunctionService extends BaseService<FunctionMapper,Function> {


    /**
     * 新增或修改Function
     * @return
     */
    public boolean saveFunction(RequestMsg requestMsg){
        JSONObject authInfo = requestMsg.getAuthInfo();
        JSONObject data = requestMsg.getData();
        int id = data.getIntValue("id");
		
		Function function = new Function();
		function.setName(data.getString("name"));
		function.setFuncKey(data.getString("funcKey"));

        if(id > 0){//修改
            Function preFunction = this.getById(id);
            if(function == null){
                throw new ErrorCodeException(-1,
                        "save-function-null","修改的数据不存在");
            }
            //将function不为空的copy到preFunction,更新function
            BeanUtils.copyProperties(function,preFunction);
            boolean result = this.updateById(preFunction);//更新
            return result;
        }else{
            //新增
            return this.save(function);//新增
        }
    }


}
