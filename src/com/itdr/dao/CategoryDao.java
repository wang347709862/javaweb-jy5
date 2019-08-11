package com.itdr.dao;

import com.itdr.pojo.Categorys;
import com.itdr.pojo.Products;
import com.itdr.pojo.Users;
import com.itdr.utils.PoolUTil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {
    //判断分类是否存在
    public boolean selectIfExited(int pid) {
        QueryRunner qr = new QueryRunner(PoolUTil.getCon());
        String sql = "select * from categorys where id =?";
        Categorys c = null;
        try {
            c = qr.query(sql, new BeanHandler<Categorys>(Categorys.class), pid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (c == null) {
            return false;
        } else {
            return true;
        }

    }

    //增加分类
    public int add_categoryDo(int pid, String categoryName) {
        QueryRunner qr = new QueryRunner(PoolUTil.getCon());
        String sql = "insert into categorys VALUES(null,?,?)";
        int row = 0;
        try {
            row = qr.update(sql, categoryName, pid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    //修改分类名称
    public int set_category_nameDo(int pid, String categoryName) {
        QueryRunner qr = new QueryRunner(PoolUTil.getCon());
        String sql = "update categorys set name = ? where id=?";
        int row = 0;
        try {
            row = qr.update(sql, categoryName, pid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;

    }

    //根据分类id查询该类的直接子节点（商品）
    public List<Products> selectProduct(int pid) {
        QueryRunner qr = new QueryRunner(PoolUTil.getCon());
        String sql = "select * from products where categoryid=?";
        List<Products> li = null;
        try {
            li = qr.query(sql, new BeanListHandler<Products>(Products.class), pid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return li;

    }

    //获取所有递归子分类id
    public void getDeepChilds(Integer parentId,List<Integer> ids){
        List<Integer> list = selectDirectCategoryId(parentId);
        if(list != null && list.size() != 0) {
            for (Integer c : list) {
                ids.add(c);
                getDeepChilds(c, ids);
            }
        }
    }

    //根据分类id查询该类的直接子节点（分类ID）
    public List<Integer> selectDirectCategoryId(int pid) {
        //从分类表查询所有分类
        QueryRunner qr = new QueryRunner(PoolUTil.getCon());
        String sql = "select id from categorys where parentid=?";
        List<Categorys> li = null;
        try {
            li = qr.query(sql, new BeanListHandler<Categorys>(Categorys.class), pid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //把查出来所有分类的id装在一个集合里
        List<Integer> list=null;
        if(li!=null){
            list=new ArrayList<>();
            for(int i=0;i<li.size();i++){
                list.add(li.get(i).getId());
            }
        }
        return list;

    }




}
