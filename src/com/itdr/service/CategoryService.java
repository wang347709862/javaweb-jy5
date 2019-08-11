package com.itdr.service;

import com.itdr.common.Const;
import com.itdr.common.ResponeCode;
import com.itdr.dao.CategoryDao;
import com.itdr.pojo.Products;

import java.util.ArrayList;
import java.util.List;

public class CategoryService {
    CategoryDao cd = new CategoryDao();

    //增加分类
    public ResponeCode add_categoryDo(String parentId, String categoryName) {
        ResponeCode rs = new ResponeCode();
        if (parentId == null || parentId.equals("") || categoryName == null || categoryName.equals("")) {
            rs.setStatus(1);
            rs.setMag("分类信息为空");
            return rs;
        }

        int pid = Integer.parseInt(parentId);
        boolean is = cd.selectIfExited(pid);
        if (!is) {
            rs.setStatus(1);
            rs.setMag("没有该父类！");
            return rs;
        } else {
            int row = cd.add_categoryDo(pid, categoryName);
            if (row == 0) {
                rs.setStatus(1);
                rs.setMag("增加失败");
                return rs;
            } else {
                rs.setStatus(0);
                rs.setMag("增加成功");
                return rs;
            }
        }

    }

    //修改分类名称
    public ResponeCode set_category_nameDo(String categoryId, String categoryName) {
        ResponeCode rs = new ResponeCode();
        if (categoryId == null || categoryId.equals("") || categoryName == null || categoryName.equals("")) {
            rs.setStatus(1);
            rs.setMag("分类信息为空");
            return rs;
        }

        int pid = Integer.parseInt(categoryId);
        boolean is = cd.selectIfExited(pid);
        if (!is) {
            rs.setStatus(1);
            rs.setMag("没有该分类！");
            return rs;
        } else {
            int row = cd.set_category_nameDo(pid, categoryName);
            if (row == 0) {
                rs.setStatus(1);
                rs.setMag("修改失败");
                return rs;
            } else {
                rs.setStatus(0);
                rs.setMag("修改成功");
                return rs;
            }
        }
    }

    //根据分类id查询该类的直接子节点（商品）
    public ResponeCode get_categoryDo(String categoryId) {
        ResponeCode rs = new ResponeCode();
        if (categoryId == null || categoryId.equals("")) {
            rs.setStatus(1);
            rs.setMag("分类信息为空");
            return rs;
        }

        int pid = Integer.parseInt(categoryId);
        boolean is = cd.selectIfExited(pid);
        if (!is) {
            rs.setStatus(1);
            rs.setMag("没有该分类！");
            return rs;
        } else {
            List<Products> li = cd.selectProduct(pid);
            if(li==null){
                rs.setStatus(1);
                rs.setMag("木得商品！");
                return rs;
            }else{
                rs.setStatus(0);
                rs.setData(li);
                return rs;
            }

        }
    }

    //获取所有递归子分类id
    public ResponeCode get_deep_categoryDo(String categoryId) {
        ResponeCode rs = new ResponeCode();
        if (categoryId == null || categoryId.equals("")) {
            rs.setStatus(1);
            rs.setMag("分类信息为空");
            return rs;
        }

        int pid = Integer.parseInt(categoryId);
        boolean is = cd.selectIfExited(pid);
        if (!is) {
            rs.setStatus(1);
            rs.setMag("没有该分类！");
            return rs;
        } else {
            List<Integer> list = new ArrayList<>();
            list.add(pid);
            cd.getDeepChilds(pid,list);
            rs.setStatus(0);
            rs.setData(list);
            return rs;
        }

    }
}
