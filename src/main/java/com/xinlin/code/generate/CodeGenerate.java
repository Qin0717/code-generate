package com.xinlin.code.generate;





import com.xinlin.code.generate.config.DataSourceConfig;
import com.xinlin.code.generate.config.GlobalConfig;
import com.xinlin.code.generate.po.TableField;
import com.xinlin.code.generate.po.TableInfo;
import com.xinlin.code.generate.utils.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CodeGenerate implements ICallBack{

  private static Logger logger = LoggerFactory.getLogger(CodeGenerate.class);


  private List<TableInfo> tableInfoList;
  private TableInfo tableInfo;
  private GlobalConfig globalConfig;
  private DataSourceConfig dataSourceConfig;



    public CodeGenerate() {

    }

    public CodeGenerate(GlobalConfig globalConfig,DataSourceConfig dataSourceConfig)
    {
        this.globalConfig = globalConfig;
        this.dataSourceConfig = dataSourceConfig;
    }



    @Override
    public Map<String, Object> execute() {

        Map data = new HashMap();
        data.put("entityPackage", globalConfig.getEntityPackage());//实体的包名
        data.put("controllerPackage", globalConfig.getControllerPackage());
        data.put("servicePackage", globalConfig.getServicePackage());
        data.put("serviceImplPackage", globalConfig.getServiceImplPackage());
        data.put("mapperPackage",globalConfig.getMapperPackage());
        //移除表前缀，表名之间的下划线，得到实体类型
        String entity = CommonUtils.getNoUnderlineStr(CommonUtils.removePrefix(tableInfo.getName().toLowerCase(),globalConfig.getPrefix()));
        data.put("entity", StringUtils.capitalize(entity));//实体名称
        data.put("author", globalConfig.getAuthor());//创建作者
        data.put("projectName", globalConfig.getProjectName());//项目名称
        data.put("date",  CommonUtils.getFormatTime("yyyy-MM-dd HH:mm:ss", new Date() ));//创建时间
        data.put("table", tableInfo);//表信息
        boolean isKeyflag = false;
        for (TableField field:tableInfo.getFields()) {
            if(field.isKeyIdentityFlag()){//获取主键字段信息
                data.put("tbKey", field.getName());
                data.put("tbKeyType", field.getPropertyType());
                isKeyflag = true;
                break;
            }
        }
        if(!isKeyflag){
            throw new RuntimeException(String.format("[%s]表缺少主键，不能没有主键",tableInfo.getName()));
        }
        return data;
    }


    /**
     * 生成代码文件
     * @return
     */
    public boolean generateToFile() {
        initConfig();
        for(TableInfo tableInfo : tableInfoList){
            this.tableInfo = tableInfo;//当前需要生成的表
            logger.info("------Code----Generation----[单表模型:" + tableInfo.getName() + "]------- 生成中。。。");
            try{

                CodeFactory codeFactory = new CodeFactory();
                codeFactory.setCallBack(this);
                codeFactory.setGlobalConfig(globalConfig);
                codeFactory.invoke("entityTemplate.ftl", "entity");
                codeFactory.invoke("controllerTemplate.ftl", "controller");
                codeFactory.invoke("serviceTemplate.ftl", "service");
                codeFactory.invoke("serviceImplTemplate.ftl", "serviceImpl");
                codeFactory.invoke("mapperTemplate.ftl", "mapper");
                if (StringUtils.isNotBlank(globalConfig.getMapperXmlPath())){
                    codeFactory.invoke("mapperXmlTemplate.ftl", "mapperXml");
                }
                logger.info("-------Code----Generation-----[单表模型：" + tableInfo.getName() + "]------ 生成完成。。。");
            }catch (Exception e){
                e.printStackTrace();
                logger.info("-------Code----Generation-----[单表模型：" + tableInfo.getName() + "]------ 生成失败。。。");
                return false;
            }
        }

        return true;
    }

    private void initConfig(){
      if(dataSourceConfig == null){
          throw new RuntimeException("dataSourceConfig is null");
      }
      if(globalConfig == null){
          throw new RuntimeException("globalConfig is null");
      }
      tableInfoList = dataSourceConfig.getTablesInfo(globalConfig.getTableNames());
    }
}
