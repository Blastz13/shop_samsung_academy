package com.example.shop;

public class CartItemModel {
    public static final int CART_ITEM = 0;
    public static final int TOTAL_AMOUNT = 1;
    private int type;
    private String productId;
    private String productImage;
    private String productTitle;
    private Long freeCoupons;
    private String productPrice;
    private String discountPrice;
    private Long productQuantity;
    private Long offersApplied;
    private Long couponApplied;
    private String totalItems;
    private String totalAmount;
    private String deliveryPrice;
    private String savedAmount;
    private String finalTotalAmount;

    public CartItemModel(int type) {
        this.type = type;
    }

    public CartItemModel(int type, String productId, String productImage, String productTitle, Long freeCoupons, String productPrice, String discountPrice, Long productQuantity, Long offersApplied, Long couponApplied) {
        this.type = type;
        this.productId = productId;
        this.productImage = productImage;
        this.productTitle = productTitle;
        this.freeCoupons = freeCoupons;
        this.productPrice = productPrice;
        this.discountPrice = discountPrice;
        this.productQuantity = productQuantity;
        this.offersApplied = offersApplied;
        this.couponApplied = couponApplied;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public Long getFreeCoupons() {
        return freeCoupons;
    }

    public void setFreeCoupons(Long freeCoupons) {
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

    public Long getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Long productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Long getOffersApplied() {
        return offersApplied;
    }

    public void setOffersApplied(Long offersApplied) {
        this.offersApplied = offersApplied;
    }

    public Long getCouponApplied() {
        return couponApplied;
    }

    public void setCouponApplied(Long couponApplied) {
        this.couponApplied = couponApplied;
    }
}
