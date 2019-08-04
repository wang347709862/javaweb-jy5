package com.itdr.pojo;

import java.math.BigDecimal;
import java.util.List;

public class DingDan {
    private String orderno;
    private BigDecimal payment;
    private Integer paymentType;
    private String paymentTypeDesc;
    private Integer postage;
    private Integer status;
    private String statusDesc;
    private String paymentTime;
    private String sendTime;
    private String endTime;
    private String closeTime;
    private String createTime;
    private List<Dingdan_Product> orderItemVoList;

    public DingDan() {
    }

    public DingDan(String orderno, BigDecimal payment, Integer paymentType, String paymentTypeDesc, Integer postage, Integer status, String statusDesc, String paymentTime, String sendTime, String endTime, String closeTime, String createTime) {
        this.orderno = orderno;
        this.payment = payment;
        this.paymentType = paymentType;
        this.paymentTypeDesc = paymentTypeDesc;
        this.postage = postage;
        this.status = status;
        this.statusDesc = statusDesc;
        this.paymentTime = paymentTime;
        this.sendTime = sendTime;
        this.endTime = endTime;
        this.closeTime = closeTime;
        this.createTime = createTime;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentTypeDesc() {
        return paymentTypeDesc;
    }

    public void setPaymentTypeDesc(String paymentTypeDesc) {
        this.paymentTypeDesc = paymentTypeDesc;
    }

    public Integer getPostage() {
        return postage;
    }

    public void setPostage(Integer postage) {
        this.postage = postage;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<Dingdan_Product> getOrderItemVoList() {
        return orderItemVoList;
    }

    public void setOrderItemVoList(List<Dingdan_Product> orderItemVoList) {
        this.orderItemVoList = orderItemVoList;
    }

    @Override
    public String toString() {
        return "DingDan{" +
                "orderno='" + orderno + '\'' +
                ", payment=" + payment +
                ", paymentType=" + paymentType +
                ", paymentTypeDesc='" + paymentTypeDesc + '\'' +
                ", postage=" + postage +
                ", status=" + status +
                ", statusDesc='" + statusDesc + '\'' +
                ", paymentTime='" + paymentTime + '\'' +
                ", sendTime='" + sendTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", closeTime='" + closeTime + '\'' +
                ", createTime='" + createTime + '\'' +
                ", orderItemVoList=" + orderItemVoList +
                '}';
    }
}
