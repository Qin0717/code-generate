package com.xinhuo.demo.controller;

import com.xinhuo.demo.model.RoleFunction;
import com.xinhuo.demo.service.RoleFunctionService;
import cn.com.pcauto.shangjia.base.exception.ErrorCodeException;
import cn.com.pcauto.shangjia.base.dto.RequestMsg;
import cn.com.pcauto.shangjia.base.dto.ResultMsg;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 描述: 
 * author: 新林
 * date: 2019-06-17
 */
@RestController
@RequestMapping(value="/roleFunction")
public class RoleFunctionController {

    @Autowired
    private RoleFunctionService roleFunctionService;

     /**
     * 获取列表
     * @param requestMsg
     * @return
     */
    @PostMapping("/list")
    public ResultMsg list(@RequestBody RequestMsg requestMsg){
        ResultMsg res = new ResultMsg(0, "success");
        QueryWrapper<RoleFunction> queryWrapper = new QueryWrapper();
//        JSONObject query = res.getData().getJSONObeject("query");
//        if(StringUtils.isNotBlank(roleFunctionFrom.getName())){
//            queryWrapper.eq("name",roleFunctionFrom.getName());//填写查询条件
//        }
        //...，更多查询条件
        int pageNo = requestMsg.getData().getIntValue("_pageNo");
        int pageSize = requestMsg.getData().getIntValue("_pageSize");
        if(pageSize<=0 || pageSize>200){
            pageSize=20;//默认一次查询20条数据
        }
        IPage<RoleFunction> page = roleFunctionService.page(new Page<>(pageNo,pageSize),queryWrapper);

        res.pubData("list",page.getRecords());
        res.pubData("_pageNo",page.getCurrent());
        res.pubData("_total",page.getTotal());
        res.pubData("_totalPage", (page.getTotal()-1)/page.getSize()+1);
        return res;
    }

    /**
     * 保存数据
     * @param requestMsg
     * @return
     */
    @PostMapping("/save")
    public ResultMsg save(@RequestBody RequestMsg requestMsg){

        boolean result = roleFunctionService.saveRoleFunction(requestMsg);
        if(result){
            return new ResultMsg(0,"success");
        }else{
            throw new ErrorCodeException(-1,
                    "save-roleFunction-error","保存失败");
        }
    }

    /**
     * 删除数据
     * @param requestMsg
     * @return
     */
    @PostMapping("/delete")
    public ResultMsg delete(@RequestBody RequestMsg requestMsg){
        int id = requestMsg.getData().getIntValue("id");
        boolean result = roleFunctionService.removeById(id);
        if(result){
            return new ResultMsg(0,"success");
        }else{
            throw new ErrorCodeException(-1,
                    "delete-roleFunction-error","删除失败");
        }
    }

    @PostMapping("/get")
    public ResultMsg get(@RequestBody RequestMsg requestMsg){
        ResultMsg resultMsg =  new ResultMsg(0,"success");
        int id = requestMsg.getData().getIntValue("id");
        RoleFunction roleFunction = roleFunctionService.getById(id);
        resultMsg.setData(BeanMap.create(roleFunction));
        return resultMsg;
    }


}
