package com.xinlin.code;

import com.xinlin.code.generate.CodeGenerate;
import com.xinlin.code.generate.config.DataSourceConfig;
import com.xinlin.code.generate.config.GlobalConfig;


public class Demo {
    public static void main(String[] args) {
        //全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        //自定义模板路径
        globalConfig.setTemplatepath("/template");
        globalConfig.setAuthor("qinzihan");
        //实体包名
        globalConfig.setEntityPackage("cn.com.bluemoon.car.model");
        //mapper包名
        globalConfig.setMapperPackage("cn.com.bluemoon.car.model");
        //mapper的xml路径
        globalConfig.setMapperXmlPath("mapper");
        //service包名
        globalConfig.setServicePackage("cn.com.bluemoon.car.model");
        globalConfig.setServiceImplPackage("cn.com.bluemoon.car.model");
        globalConfig.setControllerPackage("cn.com.bluemoon.car.model");
        //需要生成的实体

        globalConfig.setOracleTableName("T_DNM_SAMPLE");
        globalConfig.setOracleTableCommon("标本明细");
        //需要生成的实体
        globalConfig.setTableNames(new String[]{"ods_sample_detail"});



        //生成的实体移除前缀
//        globalConfig.setPrefix(new String[]{"oa_remake_"});
        //文件输出路径，不配置的话默认输出当前项目的resources/code目录下
//        globalConfig.setOutputDir("D://code/");
        //文件输出路径，不配置的话默认输出当前项目的resources/code目录下
//        globalConfig.setOutputDir("D://WorkSpace/IDEA/diary/src/main/java/");

        //数据库配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDriverName("org.postgresql.Driver");
        dsc.setUrl("jdbc:postgresql://10.1.6.222:5432/ciip?stringtype=unspecified");
//        dsc.setUrl("jdbc:mysql://121.42.162.203:3306/bluemoon?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true");
        //填写自己的数据库账号
        dsc.setUsername("postgres");
//        //填写自己的数据库密码
        dsc.setPassword("123456");
        CodeGenerate codeGenerate = new CodeGenerate(globalConfig, dsc);
        //生成代码
        codeGenerate.generateToFile();
    }
}
