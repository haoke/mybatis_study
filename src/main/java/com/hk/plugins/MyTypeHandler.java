package com.hk.plugins;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyTypeHandler implements TypeHandler<Sex> {
    @Override
    public void setParameter(PreparedStatement ps, int i, Sex parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getCode()) ;
    }

    @Override
    public Sex getResult(ResultSet rs, String columnName) throws SQLException {
        int code = rs.getInt(columnName);
        return Sex.getSexByCode(code);
    }

    @Override
    public Sex getResult(ResultSet rs, int columnIndex) throws SQLException {
        int code = rs.getInt(columnIndex);
        return Sex.getSexByCode(code);
    }

    @Override
    public Sex getResult(CallableStatement cs, int columnIndex) throws SQLException {
        int code = cs.getInt(columnIndex);
        return Sex.getSexByCode(code);
    }
}
