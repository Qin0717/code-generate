package com.xinlin.code;

import com.xinlin.code.generate.CodeGenerate;
import com.xinlin.code.generate.config.DataSourceConfig;
import com.xinlin.code.generate.config.GlobalConfig;


public class Demo {
    public static void main(String[] args) {
        //全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        //自定义模板路径
        globalConfig.setTemplatepath("/template/dic");
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

        globalConfig.setOracleTableName("T_EMT_REGISTER");
        globalConfig.setOracleTableCommon("记录设备详细信息");
        //需要生成的实体
        globalConfig.setTableNames(new String[]{"sz_ciip_wh_dic_equipment_detail"});

        //数据库配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDriverName("org.postgresql.Driver");
        dsc.setUrl("jdbc:postgresql://10.1.6.222:5432/ciip?stringtype=unspecified");
        //填写自己的数据库账号
        dsc.setUsername("postgres");
        //填写自己的数据库密码
        dsc.setPassword("123456");
        CodeGenerate codeGenerate = new CodeGenerate(globalConfig, dsc);
        //生成代码
        //ciip中cdc使用模板
//        codeGenerate.generateToFile();
        codeGenerate.generateToFileDic();
    }
}
