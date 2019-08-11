package com.itdr.controller;

import com.itdr.common.ResponeCode;
import com.itdr.pojo.Users;
import com.itdr.service.UserService;

import com.itdr.utils.JsonUtils;
import com.itdr.utils.PathUTil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet( "/manage/user/*")
public class UsersControllerServlet extends HttpServlet {


    UserService uc=new UserService();
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
                rs=listDo(request,response);
//                listDo(request,response);
                break;
            case "login":
                rs=loginDo(request,response);
//                loginDo(request,response);
                break;
            case "disableuser":
                rs=disableuserDo(request);
                break;
        }

//        返回响应数据json格式
        response.setContentType("text/json;charset=utf-8");
//        System.out.println(rs.toString());
//        System.out.println(JsonUtils.obj2String(rs));
//        response.getWriter().write(rs.toString());
        response.getWriter().write(JsonUtils.obj2String(rs));
// );
    }
    //禁用操作
    private ResponeCode disableuserDo(HttpServletRequest request) {
        ResponeCode rs=new ResponeCode();
        HttpSession session = request.getSession();
        Users u= (Users) session.getAttribute("user");
        String uid=request.getParameter("uid");
        rs=uc.selectOne(uid);
//        request.setAttribute();
        return rs;
    }

    //登录操作
    private ResponeCode loginDo(HttpServletRequest request,HttpServletResponse response) {

        String username=request.getParameter("username");
        String password=request.getParameter("password");

        //调用业务层处理业务
        ResponeCode rs = uc.selectOne(username, password);
        //获取session对象
        HttpSession session=request.getSession();
        session.setAttribute("user",rs.getData());
        return rs;
    }

    //获取用户列表操作
    private  ResponeCode listDo(HttpServletRequest request,HttpServletResponse response){

        ResponeCode rs=new ResponeCode();
        //获取session对象
        HttpSession session = request.getSession();
        Users u= (Users) session.getAttribute("user");
        if(u==null){
            rs.setStatus(3);
            rs.setMag("请登录后操作");

        }
        if(u.getType()!=1){
            rs.setStatus(3);
            rs.setMag("没有操作权限");

        }

        //获取参数
        String pageSize=request.getParameter("pageSize");
        String pageNum=request.getParameter("pageNum");



        //调用业务层处理业务
        rs=uc.selectAll(pageSize,pageNum);
//        request.setAttribute("uli",rs);
        return rs;
    }
}
