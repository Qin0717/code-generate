package ${entityPackage};

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;

/**
 * 描述: ${table.comment}
 * author: ${author}
 * date: ${date}
 */
@Data
public class ${entity}Entity implements Serializable {
 private static final long serialVersionUID = 1L;

<#-- 循环属性名称 -->
<#list table.fields as field>
<#if field.comment??>
    //${field.comment}
</#if>
    private ${field.propertyType} ${field.propertyName};
</#list>
}