package com.shinow.project.ciipserver.mq.consumer.cdc.qingdao.shinow;

import com.shinow.ciip.starter.utils.DateHelper;
import com.shinow.ciip.ciipserver.mq.consumer.cdc.AbstractCdcTableHandler;
import com.shinow.ciip.warehouse.shenzhen.shinow.dic.${entity?replace('SZDIC','')?lower_case}.ods.${entity};
import com.shinow.ciip.warehouse.shenzhen.shinow.dic.${entity?replace('SZDIC','')?lower_case}.ods.${entity}Service;
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
    private ${entity}Service ${entity?replace('SZDIC','')}Service;

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
        ${entity} ${entity?replace('SZDIC','')};
        switch (op) {
            case "CREATE":
                ${entity?replace('SZDIC','')} = new ${entity}();
        <#list table.fields as field>
            <#if field.propertyName?contains("ciip")>
            <#elseif field.propertyType == "Long">
                ${entity?replace('SZDIC','')}.set${field.propertyName?cap_first}(Long.valueOf(Objects.toString(afterObj.get("${field.propertyName}"), null)));
            <#elseif field.propertyType == "Date">
                if (Objects.toString(afterObj.get("${field.propertyName}"), null) != null) {
                    Date date = DateHelper.parse(Long.parseLong(Objects.toString(afterObj.get("${field.propertyName}"), null)));
                    ${entity?replace('SZDIC','')}.set${field.propertyName?cap_first}(DateHelper.minusHours(date, 8));
                }
            <#else>
                ${entity?replace('SZDIC','')}.set${field.propertyName?cap_first}(Objects.toString(afterObj.get("${field.propertyName}"), null));
            </#if>
        </#list>
                ${entity?replace('SZDIC','')}.setCiipCreatedTime(currentDate);
                ${entity?replace('SZDIC','')}.setCiipCreatedTimestamp(currentDate.getTime());
                ${entity?replace('SZDIC','')}.setCiipUpdateTime(currentDate);
                ${entity?replace('SZDIC','')}.setCiipUpdateTimestamp(currentDate.getTime());
                ${entity?replace('SZDIC','')}.setCiipDelete("0");
                try {
                    ${entity?replace('SZDIC','')}Service.save(${entity?replace('SZDIC','')});
                } catch (DuplicateKeyException e) {
                    log.warn("ID为：{}，${table.name}表已存在，去重处理。", ${entity?replace('SZDIC','')}.getUuid());
                }
                break;
            case "UPDATE":
                ${entity?replace('SZDIC','')} = new ${entity}();
                ${entity?replace('SZDIC','')}.setUuid(Objects.toString(afterObj.get("ID"), null));

                ${entity?replace('SZDIC','')}.setCiipUpdateTime(currentDate);
                ${entity?replace('SZDIC','')}.setCiipUpdateTimestamp(currentDate.getTime());
                ${entity?replace('SZDIC','')}Service.update(${entity?replace('SZDIC','')});
                break;
            case "DELETE":
                String uuid = Objects.toString(beforeObj.get("ID"));
                ${entity?replace('SZDIC','')}Service.logicDelete(uuid);
                break;
            default:
                log.debug("忽略操作：{}", op);
                break;
        }
    }
}
