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
    private ${entity}DAO ${entity?replace('ODS','ods')}DAO;

    @Override
    public void update(${entity} ${entity?replace('ODS','ods')}) {
        Date currentDate = new Date();
        ${entity?replace('ODS','ods')}.setCiipUpdateTimestamp(currentDate.getTime());
        UpdateWrapper<${entity}> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().eq(${entity}::getUuid, ${entity?replace('ODS','ods')}.getUuid()).eq(${entity}::getCiipDelete, "0");
        ${entity?replace('ODS','ods')}DAO.update(${entity?replace('ODS','ods')}, updateWrapper);
    }

    @Override
    public void logicDelete(String uuid) {
        LambdaQueryWrapper<${entity}> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(${entity}::getUuid, uuid).eq(${entity}::getCiipDelete, "0");
        List<${entity}> ${entity?replace('ODS','ods')}List = this.${entity?replace('ODS','ods')}DAO.selectList(queryWrapper);
        if (${entity?replace('ODS','ods')}List.size() == 0) {
            return;
        }
        UpdateWrapper<${entity}> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().eq(${entity}::getUuid, uuid);
        Date currentDate = new Date();
        ${entity} ${entity?replace('ODS','ods')} = new ${entity}();
        ${entity?replace('ODS','ods')}.setUuid(uuid);
        ${entity?replace('ODS','ods')}.setCiipUpdateTime(currentDate);
        ${entity?replace('ODS','ods')}.setCiipUpdateTimestamp(currentDate.getTime());
        ${entity?replace('ODS','ods')}.setCiipDelete("1");
        ${entity?replace('ODS','ods')}DAO.update(${entity?replace('ODS','ods')}, updateWrapper);
    }

}
