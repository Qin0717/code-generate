package com.xinlin.code.generate.config.converts;

import com.xinlin.code.generate.config.DbColumnType;
import com.xinlin.code.generate.config.ITypeConvert;

/**
 * author 张新林
 * 时间 2019/6/12 0:07
 * 描述
 */
public class OracleTypeConvert implements ITypeConvert {
    public OracleTypeConvert() {
    }

    public DbColumnType processTypeConvert(String fieldType) {
        String t = fieldType.toUpperCase();
        if (t.contains("CHAR")) {
            return DbColumnType.STRING;
        } else if (!t.contains("DATE") && !t.contains("TIMESTAMP")) {
            if (t.contains("NUMBER")) {
                if (t.matches("NUMBER\\(+\\d\\)")) {
                    return DbColumnType.INTEGER;
                } else {
                    return t.matches("NUMBER\\(+\\d{2}+\\)") ? DbColumnType.LONG : DbColumnType.DOUBLE;
                }
            } else if (t.contains("FLOAT")) {
                return DbColumnType.FLOAT;
            } else if (t.contains("clob")) {
                return DbColumnType.CLOB;
            } else if (t.contains("BLOB")) {
                return DbColumnType.OBJECT;
            } else if (t.contains("binary")) {
                return DbColumnType.BYTE_ARRAY;
            } else {
                return t.contains("RAW") ? DbColumnType.BYTE_ARRAY : DbColumnType.STRING;
            }
        } else {
            return DbColumnType.DATE;
        }
    }
}
