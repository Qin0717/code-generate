package com.shinow.project.ciipserver.mq.consumer.cdc.qingdao.shinow;

import com.shinow.ciip.starter.utils.DateHelper;
import com.shinow.ciip.ciipserver.mq.consumer.cdc.AbstractCdcTableHandler;
import com.shinow.ciip.warehouse.shenzhen.shinow.${entity?replace('SZODS','')?lower_case}.ods.${entity};
import com.shinow.ciip.warehouse.shenzhen.shinow.${entity?replace('SZODS','')?lower_case}.ods.${entity}Service;
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
    private ${entity}Service ${entity?replace('SZODS','szods')}Service;

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
        ${entity} ${entity?replace('SZODS','szods')};
        switch (op) {
            case "CREATE":
                ${entity?replace('SZODS','szods')} = new ${entity}();
        <#list table.fields as field>
            <#if field.propertyName?contains("ciip")>
            <#elseif field.propertyType == "Long">
                if (Objects.toString(afterObj.get("${field.propertyName}"), null) != null) {
                    ${entity?replace('SZODS','szods')}.set${field.propertyName?cap_first}(Long.parseLong(Objects.toString(afterObj.get("${field.propertyName}"), null)));
                }
            <#elseif field.propertyType == "Date">
                if (Objects.toString(afterObj.get("${field.propertyName}"), null) != null) {
                    Date date = DateHelper.parse(Long.parseLong(Objects.toString(afterObj.get("${field.propertyName}"), null)));
                    ${entity?replace('SZODS','szods')}.set${field.propertyName?cap_first}(DateHelper.minusHours(date, 8));
                }
            <#else>
                ${entity?replace('SZODS','szods')}.set${field.propertyName?cap_first}(Objects.toString(afterObj.get("${field.propertyName}"), null));
            </#if>
        </#list>
                ${entity?replace('SZODS','szods')}.setCiipCreatedTime(currentDate);
                ${entity?replace('SZODS','szods')}.setCiipCreatedTimestamp(currentDate.getTime());
                ${entity?replace('SZODS','szods')}.setCiipUpdateTime(currentDate);
                ${entity?replace('SZODS','szods')}.setCiipUpdateTimestamp(currentDate.getTime());
                ${entity?replace('SZODS','szods')}.setCiipDelete("0");
                try {
                    ${entity?replace('SZODS','szods')}Service.save(${entity?replace('SZODS','szods')});
                } catch (DuplicateKeyException e) {
                    log.warn("ID为：{}，${table.name}表已存在，去重处理。", ${entity?replace('SZODS','szods')}.getUuid());
                }
                break;
            case "UPDATE":
                ${entity?replace('SZODS','szods')} = new ${entity}();
                ${entity?replace('SZODS','szods')}.setUuid(Objects.toString(afterObj.get("ID"), null));

                ${entity?replace('SZODS','szods')}.setCiipUpdateTime(currentDate);
                ${entity?replace('SZODS','szods')}.setCiipUpdateTimestamp(currentDate.getTime());
                ${entity?replace('SZODS','szods')}Service.update(${entity?replace('SZODS','szods')});
                break;
            case "DELETE":
                String uuid = Objects.toString(beforeObj.get("ID"));
                ${entity?replace('SZODS','szods')}Service.logicDelete(uuid);
                break;
            default:
                log.debug("忽略操作：{}", op);
                break;
        }
    }
}
