


import lombok.Data;
import lombok.experimental.Accessors;




@Data
@ContentRowHeight(20)
@HeadRowHeight(18)
@ColumnWidth(20)
public class ModelVo {
<#-- 循环属性名称 -->
<#list dataList as field>
    @ExcelProperty("${field.value}")
    private String ${field.name};

</#list>


}
