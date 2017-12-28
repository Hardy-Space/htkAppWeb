package com.htkapp.modules.merchant.buffetFood.entity;

/**
 * 自助点餐订单商品详情表
 */
public class BuffetFoodOrderProduct {

    private Integer id;

    private Integer productId;  //产品id

    private String productName;  //产品名字

    private Integer quantity;  //数量

    private Double price;   //价格

    private String imgUrl;  //图片

    private Integer orderId;   //订单表主键id

    private Integer state;  //状态：是否新增  0否  1是

    private Integer categoryId;  //所属分类id

    private String categoryName;  //分类名

    public Integer getId() {
        return id == null ? 0 : id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getOrderId() {
        return orderId == null ? 0 : orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getState() {
        return state == null ? 0 : state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getCategoryId() {
        return categoryId == null ? 0 : categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getImgUrl() {
        return imgUrl == null ? "" : imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getCategoryName() {
        return categoryName == null ? "" : categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
