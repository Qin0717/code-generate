package ${entityPackage};

<#list table.fields as field>
<#if field.propertyType?index_of("BigDecimal")!=-1>
<#assign importBigDecimal=true/>
</#if>
<#if field.propertyType?index_of("Date")!=-1>
<#assign importDate=true/>
</#if>
</#list>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Accessors(chain = true)
@ApiModel("")
@Table(name = "`${table.name}`")
public class ${entity} {
<#-- 循环属性名称 -->
<#list table.fields as field>
    <#if field.comment??>
    /**
     * <#if field.comment!="">${field.comment}<#else >主键</#if>
     */
    </#if>
    <#if field.keyIdentityFlag>
    @Id
    @Column(name = "`${field.name}`")
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty("${field.comment}")
    <#else>
    @Column(name = "`${field.name}`")
    @ApiModelProperty("${field.comment}")
    </#if>
    private ${field.propertyType} ${field.propertyName};

</#list>
<#list table.fields as field>
    public static final String ${field.name?upper_case} = "${field.propertyName}";

    public static final String DB_${field.name?upper_case} = "${field.name}";

</#list>
    /**
     * 带默认值的实例
    */
    public static ${entity} defaultInstance() {
        ${entity} instance = new ${entity}();
        <#list table.fields as field>
            <#if field.propertyType == 'Integer' && !field.keyIdentityFlag>
        instance.${field.propertyName} = new Integer("0");
            </#if>
            <#if field.propertyType == 'Long'>
        instance.${field.propertyName} = new Long("0");
            </#if>
            <#if field.propertyType == 'String'>
        instance.${field.propertyName} = "";
            </#if>
            <#if field.propertyType == 'Boolean'>
        instance.${field.propertyName} = new Boolean("false");
            </#if>
        </#list>
        return instance;
    }
}
