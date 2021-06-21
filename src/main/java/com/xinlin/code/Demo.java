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
        //全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        //自定义模板路径
        globalConfig.setTemplatepath("/template/style4");
        globalConfig.setAuthor("ZhangXinLin");
        //实体包名
        globalConfig.setEntityPackage("cn.com.bluemoon.car.model");
        //mapper包名
        globalConfig.setMapperPackage("cn.com.bluemoon.car.mapper");
        //mapper的xml路径
        globalConfig.setMapperXmlPath("mapper");
        //service包名
        globalConfig.setServicePackage("cn.com.bluemoon.car.service");
        globalConfig.setServiceImplPackage("cn.com.bluemoon.car.service.impl");
        globalConfig.setControllerPackage("cn.com.bluemoon.car.controller");
        //需要生成的实体
//        globalConfig.setTableNames(new String[]{"diary", "upload_file","user"});
        //需要生成的实体
        globalConfig.setTableNames(new String[]{"oa_remake_car_apply","oa_remake_car_apply_approver","oa_remake_car_apply_node","oa_remake_car_authorization","oa_remake_car_carpool",
                "oa_remake_car_dispatch","oa_remake_car_driver","oa_remake_car_evaluate","oa_remake_car_fee","oa_remake_car_passenger"
                ,"oa_remake_car_route","oa_remake_car_search","oa_remake_car_site","oa_remake_car_task","oa_remake_car_vehicle","oa_remake_car_vehicle_driver"});
        //生成的实体移除前缀
        globalConfig.setPrefix(new String[]{"oa_remake_"});
        //文件输出路径，不配置的话默认输出当前项目的resources/code目录下
//        globalConfig.setOutputDir("D://code/");
        //文件输出路径，不配置的话默认输出当前项目的resources/code目录下
//        globalConfig.setOutputDir("D://WorkSpace/IDEA/diary/src/main/java/");

        //数据库配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUrl("jdbc:mysql://192.168.240.205:8066/office_auto?useUnicode=true");
//        dsc.setUrl("jdbc:mysql://121.42.162.203:3306/bluemoon?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true");
        //填写自己的数据库账号
        dsc.setUsername("aa");
//        //填写自己的数据库密码
        dsc.setPassword("123");
//        dsc.setUsername("root");
        //填写自己的数据库密码
//        dsc.setPassword("Mysise-123");
        CodeGenerate codeGenerate = new CodeGenerate(globalConfig, dsc);
        //生成代码
        codeGenerate.generateToFile();
    }
}
