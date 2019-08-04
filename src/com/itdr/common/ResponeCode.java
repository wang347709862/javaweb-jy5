package com.itdr.common;

public class ResponeCode<T> {
    private Integer status;
    private T data;
    private String mag;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMag() {
        return mag;
    }

    public void setMag(String mag) {
        this.mag = mag;
    }

    //成功时返回状态码和数据，
    // 失败时返回状态码和信息

    @Override
    public String toString() {
        if(data==null){//失败
            return "ResponeCode{" +
                    "status=" + status +
                    ", mag='" + mag + '\'' +
                    '}';
        }else{//成功
            return "ResponeCode{" +
                    "status=" + status +
                    ", data=" + data +
                    '}';
        }

    }
}
