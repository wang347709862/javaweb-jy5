package com.itdr.service;

import com.itdr.common.Const;
import com.itdr.common.ResponeCode;
import com.itdr.dao.ProductDao;
import com.itdr.pojo.Products;
import com.itdr.pojo.Users;

import java.math.BigDecimal;
import java.util.List;

public class ProductService {
        ProductDao pd=new ProductDao();

    public ResponeCode selectAll(String pageNum, String pageSize) {
        if (pageSize == null || pageSize.equals("")) {
            pageSize = "10";
        }
        if (pageNum == null || pageNum.equals("")) {
            pageNum = "1";
        }

        List<Products> li = pd.selectAll(pageSize, pageNum);
        ResponeCode rs = new ResponeCode();
        rs.setStatus(0);
        rs.setData(li);
        return rs;
    }

    public ResponeCode selectDetail(String productId) {
        ResponeCode rs=new ResponeCode();

        if(productId==null || productId.equals("")){
            rs.setStatus(Const.PRODUCT_PATAMETER_CODE);
            rs.setMag(Const.PRODUCT_PATAMETER_MSG);
            return rs;
        }

        Integer pid=null;
        try {
            pid = Integer.parseInt(productId);
        } catch (Exception e) {
            rs.setStatus(105);
            rs.setMag("输入非法参数");
            return rs;
        }

        //调用数据层查询商品详情
        Products p=pd.selectDetail(pid);
        //如果商品不存在
        if(p==null){
            rs.setStatus(Const.PRODUCT_No_CODE);
            rs.setMag(Const.PRODUCT_No_MSG);
            return rs;
        }

        //商品存在，正常返回
        rs.setStatus(0);
        rs.setData(p);
        return rs;
    }

    public ResponeCode selectOneNotDetail(String productId, String productName, String pageNum, String pageSize) {

        if (pageSize == null || pageSize.equals("")) {
            pageSize = "10";
        }
        if (pageNum == null || pageNum.equals("")) {
            pageNum = "1";
        }

        ResponeCode rs = new ResponeCode();
        List li=null;
        //正常情况应该只输入一种搜索条件
        //当同时有两种搜索条件时
        if(productId.length()>0  && productName.length()>0 ){
            rs.setStatus(Const.PRODUCT_PATAMETER_WRONG_CODE);
            rs.setMag(Const.PRODUCT_PATAMETER_WRONG_MSG);
            System.out.println("aaa");
            return rs;
        }
//        两种搜索条件全为空
        if(productId.length()==0  && productName.length()==0 ){
            rs.setStatus(Const.PRODUCT_PATAMETER_CODE);
            rs.setMag(Const.PRODUCT_PATAMETER_MSG);
            System.out.println("bbb");
            return rs;
        }
        //搜索条件是id
        if(productId.length()>0){
            Integer pid=null;
            try {
                pid = Integer.parseInt(productId);
            } catch (Exception e) {
                rs.setStatus(105);
                rs.setMag("输入非法参数");
                return rs;
            }
           li=pd.selectOneNotDetailByPid(pid,pageNum,pageSize);
        }else {//搜索条件是name
           li=pd.selectOneNotDetailByPname(productName,pageNum,pageSize);
        }
        //ResponeCode{status=0, data=[], mag='null'}可能出现这种情况，要判断li.size()==0
        if(li==null || li.size()==0){
            rs.setStatus(Const.PRODUCT_No_CODE);
            rs.setMag(Const.PRODUCT_No_MSG);
            return rs;
        }

        //商品存在，正常返回
        rs.setStatus(0);
        rs.setData(li);
        return rs;
    }

    public ResponeCode updateProductStatus(String pid, String sta) {
        ResponeCode rs = new ResponeCode();
        //输入参数为空
        if(pid==null || pid.equals("") ||sta==null ||sta.equals("")){
            rs.setStatus(Const.PRODUCT_PATAMETER_CODE);
            rs.setMag(Const.PRODUCT_PATAMETER_MSG);
            return rs;
        }

        Integer status=null;
        Integer productId=null;
        try{
            productId=Integer.parseInt(pid);
            status=Integer.parseInt(sta);
            if(status!=0 &&status!=1){//当修改状态既不为0也不为1则是非法参数
                throw new Exception();
            }
        }catch(Exception e){
            rs.setStatus(105);
            rs.setMag("输入非法参数");
            return rs;
        }

        //没问题就继续更新
        int row=pd.updateProductStatus(productId,status);

        String append=null;
        if(status==0){
            append = ",现在是下架状态";
        }else{
            append = ",现在是上架状态";
        }
        if(row==0){
            rs.setStatus(1);
            rs.setData("修改商品状态失败");
        }else{
            rs.setStatus(0);
            rs.setData("修改商品状态成功"+append);
        }
        return rs;
    }
}
