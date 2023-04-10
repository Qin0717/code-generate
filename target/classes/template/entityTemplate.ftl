package ${entityPackage};

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shinow.brick.core.tool.pojo.DataObject;

import java.util.Date;

/**
 * @author: ${author}
 * @date: ${date}
 */
@TableName(value = "${table.name}", autoResultMap = true)
public class ${entity} implements DataObject {
<#-- 循环属性名称 -->
<#list table.fields as field>
<#if field.keyIdentityFlag>
    @TableId(type = IdType.INPUT)
</#if>
    private ${field.propertyType} ${field.propertyName};
</#list>


<#-- 循环set/get方法 -->
<#list table.fields as field>
<#if field.propertyType == "Boolean">
<#assign getprefix="is"/>
<#else>
<#assign getprefix="get"/>
</#if>
    public ${field.propertyType} ${getprefix}${field.capitalName}() {
	return ${field.propertyName};
    }
    public void set${field.propertyName?cap_first}(${field.propertyType} ${field.propertyName}) {
        this.${field.propertyName} = ${field.propertyName};
    }
</#list>
}