package com.itdr.service;

import com.itdr.common.Const;
import com.itdr.common.ResponeCode;
import com.itdr.dao.DingDanDao;
import com.itdr.pojo.DingDan;

import java.util.List;

public class DingDanService {
    DingDanDao dd=new DingDanDao();

    //根据订单id查询详细信息
    public ResponeCode detailDingDan(String orderNo) {
        ResponeCode rs = new ResponeCode();

        if (orderNo == null || orderNo.equals("")) {
            rs.setStatus(Const.DINGDAN_PATAMETER_CODE);
            rs.setMag(Const.DINGDAN_PATAMETER_MSG);
            return rs;
        }

        //调用数据层查询商品详情
        DingDan d= dd.selectDetail(orderNo);

        //如果订单不存在
        if (d == null) {
            rs.setStatus(Const.DINGDAN_No_CODE);
            rs.setMag(Const.DINGDAN_No_MSG);
            return rs;
        }

        //订单存在，正常返回
        rs.setStatus(0);
        rs.setData(d);
        return rs;

    }

    //根据订单id修改发货状态
    public ResponeCode send_goodsDo(String orderNo) {
        ResponeCode rs = new ResponeCode();

        //输入参数为空
        if (orderNo == null || orderNo.equals("")) {
            rs.setStatus(Const.DINGDAN_PATAMETER_CODE);
            rs.setMag(Const.DINGDAN_PATAMETER_MSG);
            return rs;
        }

        //调用数据层查询订单
        DingDan d=dd.selectDetail(orderNo);
        if(d==null){
            rs.setStatus(Const.DINGDAN_No_CODE);
            rs.setMag(Const.DINGDAN_No_MSG);
            return rs;

        }

        //获取订单的发货状态
        int status=d.getStatus();
        if(status==11){//11代表已发货，不必再修改了
            rs.setStatus(Const.DINGDAN_HAVESENDED_CODE);
            rs.setMag(Const.DINGDAN_HAVESENDED_MSG);
            return rs;
        }

        //没有发货
        //调用数据层修改发货状态
        int row= dd.send_goodsDo(orderNo);

        //如果没有修改任意一行
        if (row==0) {
            rs.setStatus(1);
            rs.setData("发货失败");
            return rs;
        }

        //修改发货状态成功
        rs.setStatus(0);
        rs.setData("发货成功");
        return rs;

    }

    //查询所有订单
    public ResponeCode listDo(String pageSize, String pageNum) {
        ResponeCode rs = new ResponeCode();

        if (pageSize == null || pageSize.equals("")) {
            pageSize = "10";
        }
        if (pageNum == null || pageNum.equals("")) {
            pageNum = "1";
        }

        //调用数据层查询所有订单
        List li=dd.selectAllDingDan(pageSize,pageNum);
        rs.setStatus(0);
        rs.setData(li);
        return rs;


    }
}
