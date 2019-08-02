package com.itdr.controller;

import com.itdr.common.ResponeCode;
import com.itdr.service.UserService;
import com.itdr.utils.PathUTil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet( "/manage/user/*")
public class UsersControllerServlet extends HttpServlet {

    UserService uc=new UserService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //处理乱码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
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

        }





        //返回响应数据
        response.getWriter().write(rs.toString());
    }
    //
    private ResponeCode listDo(HttpServletRequest request){
        //获取参数
        String pageSize=request.getParameter("pageSize");
        String pageNum=request.getParameter("pageNum");
        //调用业务层处理业务
        return uc.selectAll(pageSize,pageNum);
    }
}
