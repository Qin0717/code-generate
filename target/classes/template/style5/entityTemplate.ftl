package ${entityPackage};

<#list table.fields as field>
<#if field.propertyType?index_of("BigDecimal")!=-1>
<#assign importBigDecimal=true/>
</#if>
<#if field.propertyType?index_of("Date")!=-1>
<#assign importDate=true/>
</#if>
</#list>
import lombok.Data;
import lombok.experimental.Accessors;

<#if importDate?exists>
import java.util.Date;
</#if>
<#if importBigDecimal?exists>
import java.math.BigDecimal;
</#if>

/**
 * 表名：${table.name}
*/
@Data
public class ${entity} {
<#-- 循环属性名称 -->
<#list table.fields as field>
    <#if field.comment??>
    /**
     * <#if field.comment!="">${field.comment}<#else >主键</#if>
     */
    </#if>
    <#if field.keyIdentityFlag>
    <#else>
    </#if>
    private ${field.propertyType} ${field.propertyName};

</#list>


}
