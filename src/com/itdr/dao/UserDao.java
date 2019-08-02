package com.itdr.dao;

import com.itdr.pojo.Users;
import com.itdr.utils.PoolUTil;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDao {
    public List selectAll(String pageSize, String pageNum) {
        ComboPooledDataSource co=PoolUTil.getCon();
        QueryRunner qr=new QueryRunner(co);
        String sql="select * from users limit ?,?";
        List<Users> li=null;
        try {
           li=qr.query(sql,new BeanListHandler<Users>(Users.class),pageNum,pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return li;
    }
}

