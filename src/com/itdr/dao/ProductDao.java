package com.itdr.dao;

import com.itdr.pojo.Products;
import com.itdr.pojo.Users;
import com.itdr.utils.PoolUTil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ProductDao {
    public List<Products> selectAll(String pageSize, String pageNum) {
        QueryRunner qr=new QueryRunner(PoolUTil.getCon());
        String sql="select * from products";
        List<Products> li=null;
        try {
            li=qr.query(sql,new BeanListHandler<Products>(Products.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return li;
    }
        //根据id查询商品详情
    public Products selectDetail(Integer pid) {

        QueryRunner qr=new QueryRunner(PoolUTil.getCon());
        String sql="select * from products where id=?";
        Products p=null;
        try {
            p=qr.query(sql,new BeanHandler<Products>(Products.class),pid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    public List selectOneNotDetailByPid(Integer pid, String pageNum, String pageSize) {
        QueryRunner qr=new QueryRunner(PoolUTil.getCon());
        String sql="select * from products where id=?";
       List li=null;
        try {
            li=qr.query(sql,new BeanListHandler<Products>(Products.class),pid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return li;
    }


    public List selectOneNotDetailByPname(String productName, String pageNum, String pageSize) {
        QueryRunner qr=new QueryRunner(PoolUTil.getCon());
        String sql="select * from products where name like ?";
        List li=null;
        try {
            //这里有过bug，写在sql语句里会变成"like %'oppo'%"的形式，改了半天
            li=qr.query(sql,new BeanListHandler<Products>(Products.class),"%"+productName+"%");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return li;
    }

    public int updateProductStatus(Integer productId, Integer status) {
        QueryRunner qr=new QueryRunner(PoolUTil.getCon());
        String sql="update products set status = ? where id=?";
        int p=0;
        try {
            p=qr.update(sql,status,productId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }
}
