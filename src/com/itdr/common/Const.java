package com.itdr.common;

public class Const {
    //用户相关状态
    public static final Integer USER_PATAMETER_CODE=101;
    public static final String USER_PATAMETER_MSG="参数为空";

    public static final Integer USER_No_CODE=103;
    public static final String USER_No_MSG="用户不存在";

    public static final Integer USER_DISABLE_CODE=104;
    public static final String USER_DISABLE_MSG="用户已被禁用";

    //商品相关状态
    public static final Integer PRODUCT_PATAMETER_CODE=105;
    public static final String PRODUCT_PATAMETER_MSG="商品参数为空";

    public static final Integer PRODUCT_PATAMETER_WRONG_CODE=106;
    public static final String PRODUCT_PATAMETER_WRONG_MSG="商品参数非法（搜索条件多于一个）";

    public static final Integer PRODUCT_No_CODE=107;
    public static final String PRODUCT_No_MSG="商品不存在";

    //订单相关状态
    public static final Integer DINGDAN_PATAMETER_CODE=108;
    public static final String DINGDAN_PATAMETER_MSG="订单参数为空";

    public static final Integer DINGDAN_No_CODE=109;
    public static final String DINGDAN_No_MSG="订单不存在";

    public static final Integer DINGDAN_HAVESENDED_CODE=110;
    public static final String DINGDAN_HAVESENDED_MSG="订单已发货，不必重复操作";

}
