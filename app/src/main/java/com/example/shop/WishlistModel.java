package com.example.shop;

public class WishlistModel {
    private int productImage;
    private int freeCoupons;
    private int totalRating;
    private String productTitle;
    private String rating;
    private String productPrice;
    private String productDiscountPrice;
    private String paymentMethod;

    public WishlistModel(int productImage, int freeCoupons, int totalRating, String productTitle, String rating, String productPrice, String productDiscountPrice, String paymentMethod) {
        this.productImage = productImage;
        this.freeCoupons = freeCoupons;
        this.totalRating = totalRating;
        this.productTitle = productTitle;
        this.rating = rating;
        this.productPrice = productPrice;
        this.productDiscountPrice = productDiscountPrice;
        this.paymentMethod = paymentMethod;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public int getFreeCoupons() {
        return freeCoupons;
    }

    public void setFreeCoupons(int freeCoupons) {
        this.freeCoupons = freeCoupons;
    }

    public int getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(int totalRating) {
        this.totalRating = totalRating;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDiscountPrice() {
        return productDiscountPrice;
    }

    public void setProductDiscountPrice(String productDiscountPrice) {
        this.productDiscountPrice = productDiscountPrice;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
