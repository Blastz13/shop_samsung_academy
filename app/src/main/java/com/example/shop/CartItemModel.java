package com.example.shop;

public class CartItemModel {
    public static final int CART_ITEM = 0;
    public static final int TOTAL_AMOUNT = 1;
    private int type;
    private int productImage;
    private String productTitle;
    private int freeCoupons;
    private String productPrice;
    private String discountPrice;
    private int productQuantity;
    private int offersApplied;
    private int couponApplied;
    private String totalItems;
    private String totalAmount;
    private String deliveryPrice;
    private String savedAmount;
    private String finalTotalAmount;

    public CartItemModel(int type, String totalItems, String totalAmount, String deliveryPrice, String finalTotalAmount , String savedAmount) {
        this.type = type;
        this.totalItems = totalItems;
        this.totalAmount = totalAmount;
        this.deliveryPrice = deliveryPrice;
        this.finalTotalAmount = finalTotalAmount;
        this.savedAmount = savedAmount;
    }

    public String getFinalTotalAmount() {
        return finalTotalAmount;
    }

    public void setFinalTotalAmount(String finalTotalAmount) {
        this.finalTotalAmount = finalTotalAmount;
    }

    public String getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(String totalItems) {
        this.totalItems = totalItems;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(String deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public String getSavedAmount() {
        return savedAmount;
    }

    public void setSavedAmount(String savedAmount) {
        this.savedAmount = savedAmount;
    }

    public CartItemModel(int type, int productImage, String productTitle, int freeCoupons, String productPrice, String discountPrice, int productQuantity, int offersApplied, int couponApplied) {
        this.type = type;
        this.productImage = productImage;
        this.productTitle = productTitle;
        this.freeCoupons = freeCoupons;
        this.productPrice = productPrice;
        this.discountPrice = discountPrice;
        this.productQuantity = productQuantity;
        this.offersApplied = offersApplied;
        this.couponApplied = couponApplied;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public int getFreeCoupons() {
        return freeCoupons;
    }

    public void setFreeCoupons(int freeCoupons) {
        this.freeCoupons = freeCoupons;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(String discountPrice) {
        this.discountPrice = discountPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getOffersApplied() {
        return offersApplied;
    }

    public void setOffersApplied(int offersApplied) {
        this.offersApplied = offersApplied;
    }

    public int getCouponApplied() {
        return couponApplied;
    }

    public void setCouponApplied(int couponApplied) {
        this.couponApplied = couponApplied;
    }
}
