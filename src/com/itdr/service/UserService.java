package com.itdr.service;

import com.itdr.common.ResponeCode;
import com.itdr.dao.UserDao;
import com.itdr.pojo.Users;

import java.util.List;

public class UserService {
    UserDao ud=new UserDao();
    public ResponeCode selectAll(String pageSize, String pageNum){
        if(pageSize==null || pageSize.equals("")){
            pageSize="10";
        }
        if(pageNum==null || pageNum.equals("")){
            pageNum="1";
        }

        List<Users> li=ud.selectAll(pageSize,pageNum);
        ResponeCode rs=new ResponeCode();
        rs.setStatus(0);
        rs.setData(li);
        return rs;
    }

    public ResponeCode selectOne(String username, String password) {
        ResponeCode rs=new ResponeCode();
        if(username==null || username.equals("") || password==null || password.equals("")){
           rs.setStatus(1);
           rs.setMag("账户或密码错误");
        }

        Users u=ud.selectOne(username,password);
        //如果用户不存在
        if(u==null){
            rs.setStatus(1);
            rs.setMag("账号或密码错误");
            return rs;
        }
        //如果不是管理员
        if(u.getType()!=1){
            rs.setStatus(2);
            rs.setMag("没有操作权限");
            return rs;
        }
        //没毛病
        rs.setStatus(0);
        rs.setData(u);
        return rs;
    }
}
