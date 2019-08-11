package com.itdr.service;

import com.itdr.common.Const;
import com.itdr.common.ResponeCode;
import com.itdr.dao.ProductDao;
import com.itdr.pojo.Products;
import com.itdr.pojo.Users;

import java.math.BigDecimal;
import java.util.List;

public class ProductService {
    ProductDao pd = new ProductDao();

    //获取所有商品列表
    public ResponeCode selectAll(String pageNum, String pageSize) {
        if (pageSize == null || pageSize.equals("")) {
            pageSize = "10";
        }
        if (pageNum == null || pageNum.equals("")) {
            pageNum = "1";
        }

        List<Products> li = pd.selectAll(pageSize, pageNum);
        ResponeCode rs = new ResponeCode();
        rs.setStatus(0);
        rs.setData(li);
        return rs;
    }

    //根据商品id查询详细信息
    public ResponeCode selectDetail(String productId) {
        ResponeCode rs = new ResponeCode();

        if (productId == null || productId.equals("")) {
            rs.setStatus(Const.PRODUCT_PATAMETER_CODE);
            rs.setMag(Const.PRODUCT_PATAMETER_MSG);
            return rs;
        }

        Integer pid = null;
        try {
            pid = Integer.parseInt(productId);
        } catch (Exception e) {
            rs.setStatus(105);
            rs.setMag("输入非法参数");
            return rs;
        }

        //调用数据层查询商品详情
        Products p = pd.selectDetail(pid);
        //如果商品不存在
        if (p == null) {
            rs.setStatus(Const.PRODUCT_No_CODE);
            rs.setMag(Const.PRODUCT_No_MSG);
            return rs;
        }

        //商品存在，正常返回
        rs.setStatus(0);
        rs.setData(p);
        return rs;
    }

    //根据id或name搜索商品
    public ResponeCode selectOneNotDetail(String productId, String productName, String pageNum, String pageSize) {

        if (pageSize == null || pageSize.equals("")) {
            pageSize = "10";
        }
        if (pageNum == null || pageNum.equals("")) {
            pageNum = "1";
        }

        ResponeCode rs = new ResponeCode();
        List li = null;
        //正常情况应该只输入一种搜索条件
        //        两种搜索条件全为空,要么没给，要么给了没值
        if ((productId==null || productId.length() == 0 )&& (productName==null || productName.length() == 0)) {
            rs.setStatus(Const.PRODUCT_PATAMETER_CODE);
            rs.setMag(Const.PRODUCT_PATAMETER_MSG);
            System.out.println("bbb");
            return rs;
        }
        //当同时有两种搜索条件时
        if (productId!=null && productName!=null && productId.length() > 0 && productName.length() > 0) {
            rs.setStatus(Const.PRODUCT_PATAMETER_WRONG_CODE);
            rs.setMag(Const.PRODUCT_PATAMETER_WRONG_MSG);
            System.out.println("aaa");
            return rs;
        }

        //搜索条件是id，这里明明不可能有空指针了还高亮，有毒
        if (productId.length() > 0) {
            Integer pid = null;
            try {
                pid = Integer.parseInt(productId);
            } catch (Exception e) {
                rs.setStatus(105);
                rs.setMag("输入非法参数");
                return rs;
            }
            li = pd.selectOneNotDetailByPid(pid, pageNum, pageSize);
        } else {//搜索条件是name
            li = pd.selectOneNotDetailByPname(productName, pageNum, pageSize);
        }
        //ResponeCode{status=0, data=[], mag='null'}可能出现这种情况，要判断li.size()==0
        if (li == null || li.size() == 0) {
            rs.setStatus(Const.PRODUCT_No_CODE);
            rs.setMag(Const.PRODUCT_No_MSG);
            return rs;
        }

        //商品存在，正常返回
        rs.setStatus(0);
        rs.setData(li);
        return rs;
    }

    //根据id改变商品上下架状态
    public ResponeCode updateProductStatus(String pid, String sta) {
        ResponeCode rs = new ResponeCode();
        //输入参数为空
        if (pid == null || pid.equals("") || sta == null || sta.equals("")) {
            rs.setStatus(Const.PRODUCT_PATAMETER_CODE);
            rs.setMag(Const.PRODUCT_PATAMETER_MSG);
            return rs;
        }

        Integer status = null;
        Integer productId = null;
        try {
            productId = Integer.parseInt(pid);
            status = Integer.parseInt(sta);
            if (status != 0 && status != 1) {//当修改状态既不为0也不为1则是非法参数
                throw new Exception();
            }
        } catch (Exception e) {
            rs.setStatus(105);
            rs.setMag("输入非法参数");
            return rs;
        }

        //判断是否有必要更新，节省资源
        Products p = pd.selectDetail(productId);
        if(p==null){
            rs.setStatus(105);
            rs.setMag("没有这个商品");
            return rs;
        }
        int st=p.getStatus();
        if(st==status){
            rs.setStatus(1);
            rs.setMag("商品状态无必要修改");
            return rs;
        }

        //有必要就继续更新
        int row = pd.updateProductStatus(productId, status);

        String append = null;
        if (status == 0) {
            append = ",现在是下架状态";
        } else {
            append = ",现在是上架状态";
        }
        if (row == 0) {
            rs.setStatus(1);
            rs.setData("修改商品状态失败");
        } else {
            rs.setStatus(0);
            rs.setData("修改商品状态成功" + append);
        }
        return rs;
    }

    //插入或更新一个商品
    public ResponeCode updateOrInsertProduct(String categoryIds, String names, String subtitles, String mainImages, String subImagess, String details, String prices, String stocks, String statuss, String ids) {
        ResponeCode rs = new ResponeCode();
        //输入参数有空（除了id）
        if (categoryIds == null || categoryIds.equals("") || names == null || names.equals("")
                || subtitles == null || subtitles.equals("") || mainImages == null || mainImages.equals("")
                || subImagess == null || subImagess.equals("") || details == null || details.equals("")
                || prices == null || prices.equals("") || stocks == null || stocks.equals("")
                || statuss == null || statuss.equals("")) {
            rs.setStatus(Const.PRODUCT_PATAMETER_CODE);
            rs.setMag(Const.PRODUCT_PATAMETER_MSG);
            return rs;
        }

        Integer categoryId = null;
        Integer price = null;
        Integer stock = null;
        Integer status = null;
        Integer id = null;
        try {
            categoryId = Integer.parseInt(categoryIds);
            price = Integer.parseInt(prices);
            stock = Integer.parseInt(stocks);
            status = Integer.parseInt(statuss);
            if(status!=0 &&status!=1) //状态不能为0和1以外的数
            {
                throw new Exception();
            }
        } catch (Exception e) {
            rs.setStatus(105);
            rs.setMag("输入非法参数");
            return rs;
        }

        int row = 0;
        //当没有id时为插入，有id时为更新
        if (ids == null || ids.equals("")) {
            row = pd.insertProduct(categoryId, names, subtitles, mainImages, subImagess, details, price, stock, status);
            if (row == 1) {
                rs.setData("插入商品成功");
                rs.setStatus(0);
                return rs;
            }
        } else {
            try {
                id = Integer.parseInt(ids);
            } catch (Exception e) {
                rs.setStatus(105);
                rs.setMag("输入非法参数");
                return rs;
            }
            row = pd.updateProduct(categoryId, names, subtitles, mainImages, subImagess, details, price, stock, status, id);
            if (row == 1) {
                rs.setData("更新商品成功");
                rs.setStatus(0);
                return rs;
            }
        }
        //返回更新行数为0
        rs.setData("更新产品失败");
        rs.setStatus(1);
        return rs;
    }

}
