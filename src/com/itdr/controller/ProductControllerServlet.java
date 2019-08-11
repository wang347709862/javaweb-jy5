package com.itdr.controller;

import com.itdr.common.ResponeCode;
import com.itdr.pojo.Users;
import com.itdr.service.ProductService;
import com.itdr.utils.JsonUtils;
import com.itdr.utils.PathUTil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/manage/product/*")
public class ProductControllerServlet extends HttpServlet {
    ProductService pc=new ProductService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求路径的信息
        String pathInfo=request.getPathInfo();
        String path=PathUTil.getPath(pathInfo);

        //创建统一返回对象
        ResponeCode rs=null;
        //判断请求种类
        switch (path){
            case "list":
                rs=listDo(request);

                break;

            case "detail":
                rs=detailDo(request,response);


                break;
            case "search":
                rs=searchDo(request);
                break;
            case "set_sale_status":
                rs=set_sale_statusDo(request);
                break;
            case "save":
                rs=savesDo(request);
                break;
//            case "upload":
//                rs=upload(request);
//                break;
        }

        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JsonUtils.obj2String(rs));

    }

    //插入或更新一个商品
    private ResponeCode savesDo(HttpServletRequest request) {
        ResponeCode rs=new ResponeCode();
        //获取session对象
        HttpSession session = request.getSession();
        Users u= (Users) session.getAttribute("user");


        //获取参数
        String categoryId=request.getParameter("categoryId");
        String name=request.getParameter("name");
        String subtitle=request.getParameter("subtitle");
        String mainImage=request.getParameter("mainImage");
        String subImages=request.getParameter("subImages");
        String detail=request.getParameter("detail");
        String price=request.getParameter("price");
        String stock=request.getParameter("stock");
        String status=request.getParameter("status");
        String id=request.getParameter("id");


        //调用业务层处理业务
        rs=pc.updateOrInsertProduct(categoryId,name,subtitle,mainImage,subImages,detail,price,stock,status,id);

        return rs;
    }

    //根据id改变商品上下架状态
    private ResponeCode set_sale_statusDo(HttpServletRequest request) {
        ResponeCode rs=new ResponeCode();
        //获取session对象
        HttpSession session = request.getSession();
        Users u= (Users) session.getAttribute("user");

        if(u==null){
            rs.setStatus(3);
            rs.setMag("请登录后操作");
            return rs;
        }
        if(u.getType()!=1){
            rs.setStatus(3);
            rs.setMag("没有操作权限");
            return rs;
        }

        //获取参数
        String productId=request.getParameter("productId");
        String status=request.getParameter("status");


        //调用业务层处理业务
        rs=pc.updateProductStatus(productId,status);

        return rs;
    }

    //根据id或name搜索商品
    private ResponeCode searchDo(HttpServletRequest request) {
        ResponeCode rs=new ResponeCode();
        //获取session对象
        HttpSession session = request.getSession();
        Users u= (Users) session.getAttribute("user");

        if(u==null){
            rs.setStatus(3);
            rs.setMag("请登录后操作");
            return rs;
        }
        if(u.getType()!=1){
            rs.setStatus(3);
            rs.setMag("没有操作权限");
            return rs;
        }

        //获取参数
        String productId=request.getParameter("productId");
        String productName=request.getParameter("productName");
        String pageNum=request.getParameter("pageNum");
        String pageSize=request.getParameter("pageSize");

        //调用业务层处理业务
        rs=pc.selectOneNotDetail(productId,productName,pageNum,pageSize);

        return rs;

    }

    //根据商品id查询详细信息
    private ResponeCode detailDo(HttpServletRequest request,HttpServletResponse response) {

        ResponeCode rs=new ResponeCode();
        //获取session对象
        HttpSession session = request.getSession();
        Users u= (Users) session.getAttribute("user");
        if(u==null){
            rs.setStatus(3);
            rs.setMag("请登录后操作");
            return rs;
        }
        if(u.getType()!=1){
            rs.setStatus(3);
            rs.setMag("没有操作权限");
            return rs;
        }

        //获取参数
        String productId=request.getParameter("productId");

        //调用业务层处理业务
        rs=pc.selectDetail(productId);
        return rs;
    }

    //获取所有商品列表
    private ResponeCode listDo(HttpServletRequest request) {
        ResponeCode rs=new ResponeCode();
        //获取session对象
        HttpSession session = request.getSession();
        Users u= (Users) session.getAttribute("user");
        if(u==null){
            rs.setStatus(3);
            rs.setMag("请登录后操作");
            return rs;
        }
        if(u.getType()!=1){
            rs.setStatus(3);
            rs.setMag("没有操作权限");
            return rs;
        }

        //获取参数
        String pageSize=request.getParameter("pageSize");
        String pageNum=request.getParameter("pageNum");

        //调用业务层处理业务
       rs=pc.selectAll(pageNum,pageSize);

        return rs;
    }
}
