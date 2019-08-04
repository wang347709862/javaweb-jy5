package com.itdr.controller;

import com.itdr.common.ResponeCode;
import com.itdr.pojo.Users;
import com.itdr.service.DingDanService;
import com.itdr.utils.PathUTil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/manage/order/*")
public class DingDanControllerServlet extends HttpServlet {
    DingDanService dc=new DingDanService();

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
            case "detail":
                rs=detailDo(request);
                break;
            case "send_goods":
                rs=send_goodsDo(request);
                break;
            case "list":
                rs=listDo(request);
                break;
        }

        //返回响应数据
        response.getWriter().write(rs.toString());
    }

    //查询所有订单
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

        //根据订单id查询详细信息
        rs=dc.listDo(pageSize,pageNum);

        return rs;

    }

    //订单发货
    private ResponeCode send_goodsDo(HttpServletRequest request) {
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
        String orderNo=request.getParameter("orderNo");

        //根据订单id查询详细信息
        rs=dc.send_goodsDo(orderNo);

        return rs;
    }

    //根据订单号查询订单详情
    private ResponeCode detailDo(HttpServletRequest request) {

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
        String orderNo=request.getParameter("orderNo");

        //根据订单id查询详细信息
        rs=dc.detailDingDan(orderNo);

        return rs;
    }
}
