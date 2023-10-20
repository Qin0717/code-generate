package ${serviceImplPackage};

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.shinow.brick.ormplus.mybatis.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * @author: ${author}
 * @date: ${date}
 */
@Service
public class ${entity}ServiceImpl extends BaseServiceImpl<${entity}> implements ${entity}Service {

    @Autowired
    private ${entity}DAO ${entity?replace('SZDIC','')}DAO;

    @Override
    public void update(${entity} ${entity?replace('SZDIC','')}) {
        Date currentDate = new Date();
        ${entity?replace('SZDIC','')}.setCiipUpdateTimestamp(currentDate.getTime());
        UpdateWrapper<${entity}> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().eq(${entity}::getUuid, ${entity?replace('SZDIC','')}.getUuid()).eq(${entity}::getCiipDelete, "0");
        ${entity?replace('SZDIC','')}DAO.update(${entity?replace('SZDIC','')}, updateWrapper);
    }

    @Override
    public void logicDelete(String uuid) {
        LambdaQueryWrapper<${entity}> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(${entity}::getUuid, uuid).eq(${entity}::getCiipDelete, "0");
        List<${entity}> ${entity?replace('SZDIC','')}List = this.${entity?replace('SZDIC','')}DAO.selectList(queryWrapper);
        if (${entity?replace('SZDIC','')}List.size() == 0) {
            return;
        }
        UpdateWrapper<${entity}> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().eq(${entity}::getUuid, uuid);
        Date currentDate = new Date();
        ${entity} ${entity?replace('SZDIC','')} = new ${entity}();
        ${entity?replace('SZDIC','')}.setUuid(uuid);
        ${entity?replace('SZDIC','')}.setCiipUpdateTime(currentDate);
        ${entity?replace('SZDIC','')}.setCiipUpdateTimestamp(currentDate.getTime());
        ${entity?replace('SZDIC','')}.setCiipDelete("1");
        ${entity?replace('SZDIC','')}DAO.update(${entity?replace('SZDIC','')}, updateWrapper);
    }

}
