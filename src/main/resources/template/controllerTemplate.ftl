package com.shinow.project.ciipserver.mq.consumer.cdc.qingdao.shinow;

import com.shinow.ciip.starter.utils.DateHelper;
import com.shinow.ciip.ciipserver.mq.consumer.cdc.AbstractCdcTableHandler;
import com.shinow.ciip.warehouse.${entity?replace('ODS','')?lower_case}.ods.${entity};
import com.shinow.ciip.warehouse.${entity?replace('ODS','')?lower_case}.ods.${entity}Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.Objects;
/**
 * @author: ${author}
 * @date: ${date}
 */
@Component
@Slf4j
public class ${cdcFileName} extends AbstractCdcTableHandler {
    @Autowired
    private ${entity}Service ${entity?replace('ODS','ods')}Service;

    <#--public ${cdcFileName}() {-->
    <#--}-->
    @Override
    public String getName() {
        return "${oracleTableName}";
    }

    @Override
    public String getDescription() {
        return "${oracleTableCommon}";
    }

    @Override
    public void handle(String op, Map< String,Object> beforeObj, Map< String, Object> afterObj) {
        Date currentDate = new Date();
        ${entity} ${entity?replace('ODS','ods')};
        switch (op) {
            case "CREATE":
                ${entity?replace('ODS','ods')} = new ${entity}();
        <#list table.fields as field>
            <#if field.propertyName?contains("ciip")>
            <#elseif field.propertyType == "Date">
                if (Objects.toString(afterObj.get("${field.propertyName}"), null) != null) {
                    Date date = DateHelper.parse(Long.parseLong(Objects.toString(afterObj.get("${field.propertyName}"), null)));
                    ${entity?replace('ODS','ods')}.set${field.propertyName?cap_first}(DateHelper.minusHours(date, 8));
                }
            <#else>
                ${entity?replace('ODS','ods')}.set${field.propertyName?cap_first}(Objects.toString(afterObj.get("${field.propertyName}"), null));
            </#if>
        </#list>
                ${entity?replace('ODS','ods')}.setCiipCreatedTime(currentDate);
                ${entity?replace('ODS','ods')}.setCiipCreatedTimestamp(currentDate.getTime());
                ${entity?replace('ODS','ods')}.setCiipUpdateTime(currentDate);
                ${entity?replace('ODS','ods')}.setCiipUpdateTimestamp(currentDate.getTime());
                ${entity?replace('ODS','ods')}.setCiipDelete("0");
                try {
                    ${entity?replace('ODS','ods')}Service.save(${entity?replace('ODS','ods')});
                } catch (DuplicateKeyException e) {
                    log.warn("ID为：{}，${table.name}表已存在，去重处理。", ${entity?replace('ODS','ods')}.getUuid());
                }
                break;
            case "UPDATE":
                ${entity?replace('ODS','ods')} = new ${entity}();
                ${entity?replace('ODS','ods')}.setUuid(Objects.toString(afterObj.get("ID"), null));

                ${entity?replace('ODS','ods')}.setCiipUpdateTime(currentDate);
                ${entity?replace('ODS','ods')}.setCiipUpdateTimestamp(currentDate.getTime());
                ${entity?replace('ODS','ods')}Service.update(${entity?replace('ODS','ods')});
                break;
            case "DELETE":
                String uuid = Objects.toString(beforeObj.get("ID"));
                ${entity?replace('ODS','ods')}Service.logicDelete(uuid);
                break;
            default:
                log.debug("忽略操作：{}", op);
                break;
        }
    }
}
