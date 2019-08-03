package com.itdr.service;

import com.itdr.common.Const;
import com.itdr.common.ResponeCode;
import com.itdr.dao.UserDao;
import com.itdr.pojo.Users;

import java.util.List;

public class UserService {
    UserDao ud = new UserDao();
    //查询用户列表
    public ResponeCode selectAll(String pageSize, String pageNum) {
        if (pageSize == null || pageSize.equals("")) {
            pageSize = "10";
        }
        if (pageNum == null || pageNum.equals("")) {
            pageNum = "1";
        }

        List<Users> li = ud.selectAll(pageSize, pageNum);
        ResponeCode rs = new ResponeCode();
        rs.setStatus(0);
        rs.setData(li);
        return rs;
    }

    //后台系统管理员登录
    public ResponeCode selectOne(String username, String password) {
        ResponeCode rs = new ResponeCode();
        if (username == null || username.equals("") || password == null || password.equals("")) {
            rs.setStatus(1);
            rs.setMag("账户或密码错误");
        }

        Users u = ud.selectOne(username, password);
        //如果用户不存在
        if (u == null) {
            rs.setStatus(1);
            rs.setMag("账号或密码错误");
            return rs;
        }
        //如果不是管理员
        if (u.getType() != 1) {
            rs.setStatus(2);
            rs.setMag("没有操作权限");
            return rs;
        }
        //没毛病
        rs.setStatus(0);
        rs.setData(u);
        return rs;
    }

    //根据id禁用用户
    public ResponeCode selectOne(String uids) {
        ResponeCode rs = new ResponeCode();
        if (uids == null || uids.equals("")) {
            rs.setStatus(Const.USER_PATAMETER_CODE);
            rs.setMag(Const.USER_PATAMETER_MSG);
            return rs;
        }

        Integer uid=null;
        try {
            uid = Integer.parseInt(uids);
        } catch (Exception e) {
            rs.setStatus(105);
            rs.setMag("输入非法参数");
            return rs;
        }

        Users u = ud.selectOne(uid);
        //如果用户不存在
        if (u == null) {
            rs.setStatus(Const.USER_No_CODE);
            rs.setMag(Const.USER_No_MSG);
            return rs;
        }
        //如果已被禁用
        if (u.getStats() == 1) {
            rs.setStatus(Const.USER_DISABLE_CODE);
            rs.setMag(Const.USER_DISABLE_MSG);
            return rs;
        }
        //禁用用户
        int row=ud.updateByuid(uid);
        if(row<=0){
            rs.setStatus(106);
            rs.setMag("用户禁用操作失败");
            return rs;
        }
        rs.setStatus(0);
        rs.setData(row);
        return rs;
    }


}
