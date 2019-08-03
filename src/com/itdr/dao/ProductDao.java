package com.itdr.dao;

import com.itdr.pojo.Products;
import com.itdr.utils.PoolUTil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ProductDao {

    //获取所有商品列表
    public List<Products> selectAll(String pageSize, String pageNum) {
        QueryRunner qr = new QueryRunner(PoolUTil.getCon());
        String sql = "select * from products";
        List<Products> li = null;
        try {
            li = qr.query(sql, new BeanListHandler<Products>(Products.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return li;
    }

    //根据id查询商品详情
    public Products selectDetail(Integer pid) {

        QueryRunner qr = new QueryRunner(PoolUTil.getCon());
        String sql = "select * from products where id=?";
        Products p = null;
        try {
            p = qr.query(sql, new BeanHandler<Products>(Products.class), pid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    //根据id搜索商品
    public List selectOneNotDetailByPid(Integer pid, String pageNum, String pageSize) {
        QueryRunner qr = new QueryRunner(PoolUTil.getCon());
        String sql = "select * from products where id=?";
        List li = null;
        try {
            li = qr.query(sql, new BeanListHandler<Products>(Products.class), pid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return li;
    }

    //根据name模糊搜索商品
    public List selectOneNotDetailByPname(String productName, String pageNum, String pageSize) {
        QueryRunner qr = new QueryRunner(PoolUTil.getCon());
        String sql = "select * from products where name like ?";
        List li = null;
        try {
            //这里有过bug，写在sql语句里会变成"like %'oppo'%"的形式，改了半天
            li = qr.query(sql, new BeanListHandler<Products>(Products.class), "%" + productName + "%");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return li;
    }

    //根据id改变商品上下架状态
    public int updateProductStatus(Integer productId, Integer status) {
        QueryRunner qr = new QueryRunner(PoolUTil.getCon());
        String sql = "update products set status = ? where id=?";
        int p = 0;
        try {
            p = qr.update(sql, status, productId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    //没给id就是增加商品信息
    public int insertProduct(Integer categoryId, String names, String subtitles, String mainImages, String subImagess, String details, Integer price, Integer stock, Integer status) {
        QueryRunner qr = new QueryRunner(PoolUTil.getCon());
        //注意id自动递增加个null
        String sql = "insert into products (id,categoryId,name,subtitle,mainImage,subImages,detail,price,stock,status) values (null,?,?,?,?,?,?,?,?,?)";
        int row = 0;
        try {

            row = qr.update(sql, categoryId, names, subtitles, mainImages, subImagess, details, price, stock, status);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;

    }

    //给了id就是按id更新商品信息
    public int updateProduct(Integer categoryId, String names, String subtitles, String mainImages, String subImagess, String details, Integer price, Integer stock, Integer status, Integer id) {
        QueryRunner qr = new QueryRunner(PoolUTil.getCon());
        String sql = "update products set categoryId=?,name=?,subtitle=?,mainImage=?,subImages=?," +
                "detail=?,price=?,stock=?,status=? where id=?";
        int row = 0;
        try {

            row = qr.update(sql, categoryId, names, subtitles, mainImages, subImagess, details, price, stock, status, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }
}
