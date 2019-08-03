package com.itdr.pojo;


import java.math.BigDecimal;

public class Products {
    private Integer id;
    private Integer categoryid;
    private Integer parentCategoryId;
    private String name;
    private String subtitle;//标题
    private String imagehost;//图片地址
    private String mainImage;//主图片
    private String subImages;//子图片
    private String detail;//详情
    private BigDecimal price;
    private Integer stock;//库存
    private Integer status;//是否上架
    private String createtime;//创建时间
    private String updateTime;//更新时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public Integer getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Integer parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getImagehost() {
        return imagehost;
    }

    public void setImagehost(String imagehost) {
        this.imagehost = imagehost;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public String getSubImages() {
        return subImages;
    }

    public void setSubImages(String subImages) {
        this.subImages = subImages;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", categoryid=" + categoryid +
                ", parentCategoryId=" + parentCategoryId +
                ", name='" + name + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", imagehost='" + imagehost + '\'' +
                ", mainImage='" + mainImage + '\'' +
                ", subImages='" + subImages + '\'' +
                ", detail='" + detail + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", status=" + status +
                ", createtime='" + createtime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
