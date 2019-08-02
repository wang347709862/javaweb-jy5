package com.itdr.service;

import com.itdr.common.ResponeCode;
import com.itdr.dao.UserDao;
import com.itdr.pojo.Users;

import java.util.List;

public class UserService {
    public ResponeCode selectAll(String pageSize, String pageNum){
        if(pageSize==null){
            pageSize="10";
        }
        if(pageNum==null){
            pageNum="1";
        }
        UserDao ud=new UserDao();
       List<Users> li=ud.selectAll(pageSize,pageNum);
        ResponeCode rs=new ResponeCode();
        rs.setStatus(0);
        rs.setData(li);
        return rs;
    }

}
