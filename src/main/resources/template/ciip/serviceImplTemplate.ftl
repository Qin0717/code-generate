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
    private ${entity}DAO ${entity?replace('SZODS','szods')}DAO;

    @Override
    public void update(${entity} ${entity?replace('SZODS','szods')}) {
        Date currentDate = new Date();
        ${entity?replace('SZODS','szods')}.setCiipUpdateTimestamp(currentDate.getTime());
        UpdateWrapper<${entity}> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().eq(${entity}::getUuid, ${entity?replace('SZODS','szods')}.getUuid()).eq(${entity}::getCiipDelete, "0");
        ${entity?replace('SZODS','szods')}DAO.update(${entity?replace('SZODS','szods')}, updateWrapper);
    }

    @Override
    public void logicDelete(String uuid) {
        LambdaQueryWrapper<${entity}> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(${entity}::getUuid, uuid).eq(${entity}::getCiipDelete, "0");
        List<${entity}> ${entity?replace('SZODS','szods')}List = this.${entity?replace('SZODS','szods')}DAO.selectList(queryWrapper);
        if (${entity?replace('SZODS','szods')}List.size() == 0) {
            return;
        }
        UpdateWrapper<${entity}> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().eq(${entity}::getUuid, uuid);
        Date currentDate = new Date();
        ${entity} ${entity?replace('SZODS','szods')} = new ${entity}();
        ${entity?replace('SZODS','szods')}.setUuid(uuid);
        ${entity?replace('SZODS','szods')}.setCiipUpdateTime(currentDate);
        ${entity?replace('SZODS','szods')}.setCiipUpdateTimestamp(currentDate.getTime());
        ${entity?replace('SZODS','szods')}.setCiipDelete("1");
        ${entity?replace('SZODS','szods')}DAO.update(${entity?replace('SZODS','szods')}, updateWrapper);
    }

}
