package com.itdr.controller;

import com.itdr.common.ResponeCode;
import com.itdr.pojo.Users;
import com.itdr.service.UserService;
import com.itdr.utils.PathUTil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
                rs=listDo(request);
                break;
            case "login":
                rs=loginDo(request);
                break;
            case "disableuser":
                rs=disableuserDo(request);
                break;
        }

        //返回响应数据
        response.getWriter().write(rs.toString());
    }
    //禁用操作
    private ResponeCode disableuserDo(HttpServletRequest request) {
        String uid=request.getParameter("uid");
        ResponeCode rs=uc.selectOne(uid);
        return rs;
    }

    //登录操作
    private ResponeCode loginDo(HttpServletRequest request) {


        String username=request.getParameter("username");
        String password=request.getParameter("password");

        ResponeCode rs = uc.selectOne(username, password);
        //获取session对象
        HttpSession session=request.getSession();
        session.setAttribute("user",rs.getData());
        //调用业务层处理业务
        return rs;
    }

    //获取用户列表操作
    private ResponeCode listDo(HttpServletRequest request){

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
        rs=uc.selectAll(pageSize,pageNum);

        return rs;
    }
}
