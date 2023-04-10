package com.xinlin.code.generate.config;

import com.xinlin.code.generate.config.converts.MySqlTypeConvert;
import com.xinlin.code.generate.config.converts.OracleTypeConvert;
import com.xinlin.code.generate.config.converts.PostgreSqlTypeConvert;
import com.xinlin.code.generate.po.TableField;
import com.xinlin.code.generate.po.TableInfo;
import com.xinlin.code.generate.utils.CommonUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * author 张新林
 * 时间 2019/6/12 0:05
 * 描述
 */
public class DataSourceConfig {
    private DbType dbType;
    private String schemaname = "public";
    private ITypeConvert typeConvert;
    private String url;
    private String driverName;
    private String username;
    private String password;
    private Connection conn = null;

    public DataSourceConfig() {
    }

    public DbType getDbType() {
        if (null == this.dbType) {
            if (this.driverName.contains("mysql")) {
                this.dbType = DbType.MYSQL;
            } else if (this.driverName.contains("oracle")) {
                this.dbType = DbType.ORACLE;
            } else {
                if (!this.driverName.contains("postgresql")) {
                    throw new RuntimeException("Unknown type of database!");
                }

                this.dbType = DbType.POSTGRE_SQL;
            }
        }

        return this.dbType;
    }

    /**
     * 获取数据库下的所有表名
     */
    public  List<String> getTableNames() {
        List<String> tableNames = new ArrayList();
        Connection conn = getConn();
        ResultSet rs = null;
        try {
            //获取数据库的元数据
            DatabaseMetaData db = conn.getMetaData();
            //从元数据中获取到所有的表名
            rs = db.getTables(null, null, null, new String[]{"TABLE"});
            while (rs.next()) {
                tableNames.add(rs.getString(3));
            }
            rs.close();
        } catch (SQLException e) {
//            LOGGER.error("getTableNames failure", e);
        } finally {
            closeConnection();
        }
        return tableNames;
    }

    public DataSourceConfig setDbType(DbType dbType) {
        this.dbType = dbType;
        return this;
    }

    public String getSchemaname() {
        return this.schemaname;
    }

    public void setSchemaname(String schemaname) {
        this.schemaname = schemaname;
    }

    public ITypeConvert getTypeConvert() {
        if (null == this.typeConvert) {
            switch(this.getDbType()) {
                case ORACLE:
                    this.typeConvert = new OracleTypeConvert();
                    break;
                case POSTGRE_SQL:
                    this.typeConvert = new PostgreSqlTypeConvert();
                    break;
                default:
                    this.typeConvert = new MySqlTypeConvert();
            }
        }

        return this.typeConvert;
    }

    public DataSourceConfig setTypeConvert(ITypeConvert typeConvert) {
        this.typeConvert = typeConvert;
        return this;
    }

    public Connection getConn() {
        if(conn != null){
            return conn;
        }
        try {
            Class.forName(this.driverName);
//            conn = DriverManager.getConnection(this.url, this.username, this.password);
            Properties props =new Properties();
            props.setProperty("user", this.username);
            props.setProperty("password", this.password );
            props.setProperty("remarks", "true");
            props.setProperty("useInformationSchema", "true");//mysql设置可以获取tables remarks信息
            conn = DriverManager.getConnection(this.url, props);
        } catch (SQLException var2) {
            var2.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return conn;
    }
    /**
     * 关闭数据库连接
     * @param
     */
    public void closeConnection() {
        try {
            if(conn!=null){
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("close connection failure", e);
        }
    }

    /**
     * 获取生成的表信息
     * @param tables
     * @return
     */
    public List<TableInfo> getTablesInfo(String[] tables) {
//        System.out.println(getTableNames().toString());
        List<TableInfo> tableList = new ArrayList();
        for(int i = 0; i < tables.length; ++i){
            TableInfo tableInfo = new TableInfo();
            tableInfo.setName(tables[i]);
            List<TableField> fieldList = this.getTbfrFields(tableInfo.getName());
            tableInfo.setFields(fieldList);
            tableList.add(tableInfo);
        }
//        closeConnection();
        return tableList;

    }

    /**
     * 根据表名获取该表的所有字段
     * @param tableName 表名称
     * @return
     */
    public List<TableField> getTbfrFields(String tableName){
        List<TableField> tbColumns = new ArrayList();
        //与数据库的连接
        Connection conn = getConn();
        Statement stm = null;
        ResultSet rs = null;
        try {
            stm = conn.createStatement();
            ResultSet rsKey = conn.getMetaData().getPrimaryKeys(null,null,tableName);
            String keyName=null;
            while(rsKey.next()){
                keyName = rsKey.getString("COLUMN_NAME").toLowerCase();
                keyName = CommonUtils.getNoUnderlineStr(keyName);
            }
            rs = conn.getMetaData().getColumns(   null, getSchema(conn),tableName, "%");
            while(rs.next()){
                TableField tbfrField = new TableField();
                String fieldNm = rs.getString("COLUMN_NAME").toLowerCase();
                tbfrField.setName(fieldNm);//表字段名
                tbfrField.setPropertyName(CommonUtils.getNoUnderlineStr(fieldNm));//字段名
                tbfrField.setComment(rs.getString("REMARKS"));//字段注释
                tbfrField.setType(rs.getString("TYPE_NAME"));//字段类型
                tbfrField.setColumnType(this.getTypeConvert().processTypeConvert(tbfrField.getType()));

                if(keyName!=null && keyName.equals(tbfrField.getName())){
                    tbfrField.setKeyIdentityFlag(true);
                }else{
                    tbfrField.setKeyIdentityFlag(false);
                }

                tbColumns.add(tbfrField);
            }
            if(stm!=null){
                stm.close();
            }
            if(rs!=null){
                rs.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("getColumnNames failure", e);
        } catch (Exception e) {
            throw new RuntimeException("Exception rs failure", e);
        }
        return tbColumns;
    }

    //其他数据库不需要这个方法 oracle需要
    private static String getSchema(Connection conn) throws Exception {
        if(!DbType.ORACLE.getValue().equals("oracle")){
            //不是oracleshuj
            return null;
        }
        String schema;
        schema = conn.getMetaData().getUserName();
        if ((schema == null) || (schema.length() == 0)) {
            throw new Exception("ORACLE数据库模式不允许为空");
        }
        return schema.toUpperCase();

    }


    public String getUrl() {
        return this.url;
    }

    public DataSourceConfig setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getDriverName() {
        return this.driverName;
    }

    public DataSourceConfig setDriverName(String driverName) {
        this.driverName = driverName;
        return this;
    }

    public String getUsername() {
        return this.username;
    }

    public DataSourceConfig setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return this.password;
    }

    public DataSourceConfig setPassword(String password) {
        this.password = password;
        return this;
    }
}