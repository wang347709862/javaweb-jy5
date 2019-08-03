package com.itdr.utils;

import com.itdr.common.ResponeCode;
import com.itdr.pojo.Users;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "JurisDictionFilter",value="/manage/*")
public class JurisDictionFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //处理乱码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        //向下转型使用获取路径的方法
        HttpServletRequest request=(HttpServletRequest)req;
        String pathInfo=request.getPathInfo();

        //如果是登录则不需要判断因为登录模块会判断
        if(pathInfo.equals("/login.do")){
            chain.doFilter(req, resp);
            return;
        }

        ResponeCode rs=new ResponeCode();
        HttpSession session=request.getSession();
        Users user = (Users)session.getAttribute("user");

        //session已过期，没有用户了
        if(user==null){
            rs.setStatus(3);
            rs.setMag("请登录后操作");
            resp.getWriter().write(rs.toString());
            return ;
        }

        if(user.getType()!=1){
            rs.setStatus(3);
            rs.setMag("没有操作权限");
            resp.getWriter().write(rs.toString());
            return ;
        }

        //没有问题，放行
        chain.doFilter(req,resp);


    }

    public void init(FilterConfig config) throws ServletException {

    }

}
