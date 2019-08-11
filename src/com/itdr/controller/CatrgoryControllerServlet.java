package com.itdr.controller;

import com.itdr.common.ResponeCode;
import com.itdr.pojo.Users;
import com.itdr.service.CategoryService;
import com.itdr.utils.JsonUtils;
import com.itdr.utils.PathUTil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/manage/category/*")
public class CatrgoryControllerServlet extends HttpServlet {
    CategoryService cc=new CategoryService();
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
            case "add_category":
                rs=add_categoryDo(request);
                break;

            case "set_category_name":
                rs=set_category_nameDo(request);
                break;
            case "get_category":
                rs=get_categoryDo(request);
                break;
            case "get_deep_category":
                rs=get_deep_categoryDo(request);
                break;

        }
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JsonUtils.obj2String(rs));
    }

    //获取所有递归子分类id
    private ResponeCode get_deep_categoryDo(HttpServletRequest request) {
        ResponeCode rs=new ResponeCode();

        //获取参数
        String categoryId=request.getParameter("categoryId");

        //调用业务层处理业务
        rs=cc.get_deep_categoryDo(categoryId);
        return rs;

    }

    //根据分类id查询该类的直接子节点（商品）
    private ResponeCode get_categoryDo(HttpServletRequest request) {
        ResponeCode rs=new ResponeCode();

        //获取参数
        String categoryId=request.getParameter("categoryId");

        //调用业务层处理业务
        rs=cc.get_categoryDo(categoryId);
        return rs;
    }

    //修改分类名称
    private ResponeCode set_category_nameDo(HttpServletRequest request) {
        ResponeCode rs=new ResponeCode();

        //获取参数
        String categoryId=request.getParameter("categoryId");
        String categoryName=request.getParameter("categoryName");


        //调用业务层处理业务
        rs=cc.set_category_nameDo(categoryId,categoryName);
        return rs;
    }

    //增加分类
    private ResponeCode add_categoryDo(HttpServletRequest request) {
        ResponeCode rs=new ResponeCode();
        //获取session对象
        HttpSession session = request.getSession();
        Users u= (Users) session.getAttribute("user");

        //获取参数
        String parentId=request.getParameter("parentId");
        String categoryName=request.getParameter("categoryName");


        //调用业务层处理业务
        rs=cc.add_categoryDo(parentId,categoryName);
        return rs;


    }
}
