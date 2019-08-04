package com.itdr.dao;

import com.itdr.pojo.DingDan;
import com.itdr.pojo.Dingdan_Product;
import com.itdr.utils.PoolUTil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class DingDanDao {

    //根据订单id查询详情
    public DingDan selectDetail(String orderNo) {
        //从dingdan表中获取订单中除了orderItemVoList的所有字段
        QueryRunner qr = new QueryRunner(PoolUTil.getCon());
        String sql = "select * from dingdan where orderno=?";
        DingDan d = null;
        try {
            d = qr.query(sql, new BeanHandler<DingDan>(DingDan.class),orderNo);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //从dingdan_product表中获取订单中的orderItemVoList即所有商品
        if(d!=null){
            String sql2 = "select * from dingdan_product where orderno=?";
            List<Dingdan_Product> d2 = null;
            try {
                d2 = qr.query(sql2, new BeanListHandler<Dingdan_Product>(Dingdan_Product.class),orderNo);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //把所有商品的列表放入订单对象中
            d.setOrderItemVoList(d2);
        }
        return d;
    }

    //修改订单为已发货状态
    public int send_goodsDo(String orderNo) {

        QueryRunner qr = new QueryRunner(PoolUTil.getCon());
        String sql = "update dingdan set status =11 where orderno=?";
        int row =0;
        try {
            row = qr.update(sql,orderNo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    //查询所有订单
    public List selectAllDingDan(String pageSize, String pageNum) {

        //从dingdan表中获取订单中除了orderItemVoList的所有字段
        QueryRunner qr = new QueryRunner(PoolUTil.getCon());
        String sql = "select * from dingdan";
        List<DingDan> li= null;
        try {
            li = qr.query(sql, new BeanListHandler<DingDan>(DingDan.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(li==null){
            return li;
        }

        for(int i=0;i<li.size();i++){
            DingDan d=li.get(i);//取出每一个订单对象
            String orderno=d.getOrderno();//获得它的订单号

            //从dingdan_product表中根据订单号，获取订单中的orderItemVoList即所有商品
            String sql2 = "select * from dingdan_product where orderno=?";

            List<Dingdan_Product> d2 = null;
            try {
                d2 = qr.query(sql2, new BeanListHandler<Dingdan_Product>(Dingdan_Product.class),orderno);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //把所有商品的列表放入订单对象中
            d.setOrderItemVoList(d2);
        }
        return li;//返回所有订单对象
    }
}
