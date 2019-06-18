package com.xinlin.code;

import com.xinlin.code.generate.CodeGenerate;
import com.xinlin.code.generate.config.DataSourceConfig;
import com.xinlin.code.generate.config.GlobalConfig;

/**
 * @Auther: zhangxinlin
 * @Date: 2019/6/17 20:20:35
 * @Description:
 */
public class Demo {
    public static void main(String[] args) {
        GlobalConfig globalConfig = new GlobalConfig();//全局配置
//        globalConfig.setTemplatepath("/template/style1");//自定义模板路径
        globalConfig.setAuthor("新林");
        globalConfig.setEntityPackage("com.xinhuo.demo.entity");//实体包名
        globalConfig.setMapperPackage("com.xinhuo.demo.dao");//dao包名
        globalConfig.setServicePackage("com.xinhuo.demo.service");//service包名
        globalConfig.setServiceImplPackage("com.xinhuo.demo.service.impl");
        globalConfig.setControllerPackage("com.xinhuo.demo.controller");
        globalConfig.setTableNames(new String[]{"pre_user", "pre_student"});//需要生成的实体
        globalConfig.setPrefix(new String[]{"pre_"});//生成的实体移除前缀
//        globalConfig.setOutputDir("D://code/");//文件输出路径，不配置的话默认输出当前项目的resources/code目录下

        DataSourceConfig dsc = new DataSourceConfig();//数据库配置
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUrl("jdbc:mysql://192.168.33.76:3306/test?useUnicode=true&amp;characterEncoding=UTF-8&amp;&useSSL=false");
        dsc.setUsername("root");//填写自己的数据库账号
        dsc.setPassword("root");//填写自己的数据库密码
        CodeGenerate codeGenerate = new CodeGenerate(globalConfig, dsc);
        //生成代码
        codeGenerate.generateToFile();
    }
}
