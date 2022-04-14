package com.example.shop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter {

    private List<CartItemModel> cartItemModelList;

    public CartAdapter(List<CartItemModel> cartItemModelList) {
        this.cartItemModelList = cartItemModelList;
    }

    @Override
    public int getItemViewType(int position) {
        switch (cartItemModelList.get(position).getType()){
            case 0:
                return CartItemModel.CART_ITEM;
            case 1:
                return CartItemModel.TOTAL_AMOUNT;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case CartItemModel.CART_ITEM:
                View cartItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout, parent, false);
                return new CartItemViewholder(cartItemView);
            case CartItemModel.TOTAL_AMOUNT:
                View cartTotalView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_total_amount_layout, parent, false);
                return new CartTotalAmountViewholder(cartTotalView);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (cartItemModelList.get(position).getType()){
            case CartItemModel.CART_ITEM:
                int resource = cartItemModelList.get(position).getProductImage();
                String title = cartItemModelList.get(position).getProductTitle();
                int freeCoupons = cartItemModelList.get(position).getFreeCoupons();
                String productPrice = cartItemModelList.get(position).getProductPrice();
                String discountPrice = cartItemModelList.get(position).getDiscountPrice();
                int offersApplied = cartItemModelList.get(position).getOffersApplied();

                ((CartItemViewholder)holder).setItemDetails(resource, title, freeCoupons, productPrice, discountPrice, offersApplied);
                break;
            case CartItemModel.TOTAL_AMOUNT:
                String totalItems = cartItemModelList.get(position).getTotalItems();
                String totalItemPrice = cartItemModelList.get(position).getTotalAmount();
                String deliveryPrice = cartItemModelList.get(position).getDeliveryPrice();
                String finalTotalAmount = cartItemModelList.get(position).getFinalTotalAmount();
                String savedAmount = cartItemModelList.get(position).getSavedAmount();

                ((CartTotalAmountViewholder)holder).setTotalAmount(totalItems, totalItemPrice, deliveryPrice, finalTotalAmount, savedAmount);
                break;
            default:
                return;

        }
    }

    @Override
    public int getItemCount() {
        return cartItemModelList.size();
    }

    class CartItemViewholder extends RecyclerView.ViewHolder{
        private ImageView productImage;
        private ImageView freeCouponIcon;
        private TextView productTitle;
        private TextView freeCoupons;
        private TextView productPrice;
        private TextView discountPrice;
        private TextView offersApplied;
        private TextView couponsApplied;
        private TextView productQuantity;

        public CartItemViewholder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image_cart);
            freeCouponIcon = itemView.findViewById(R.id.coupon_icon);
            productTitle = itemView.findViewById(R.id.product_title_cart);
            freeCoupons = itemView.findViewById(R.id.coupon_text);
            productPrice = itemView.findViewById(R.id.product_price_cart);
            discountPrice = itemView.findViewById(R.id.discount_price_cart);
            offersApplied = itemView.findViewById(R.id.offers_applied);
            couponsApplied = itemView.findViewById(R.id.coupon_applied_cart);
            productQuantity = itemView.findViewById(R.id.product_quantity_cart);
        }

        private void setItemDetails(int resource, String title, int countFreeCoupons, String productPriceText, String discountProductPriceText, int countOffersApplied){
            productImage.setImageResource(resource);
            productTitle.setText(title);
            if(countFreeCoupons > 0){
                freeCoupons.setVisibility(View.VISIBLE);
                freeCouponIcon.setVisibility(View.VISIBLE);
                if(countFreeCoupons == 1){
                    freeCoupons.setText("free " + countFreeCoupons + " coupon");
                }
                else{
                    freeCoupons.setText("free " + countFreeCoupons + " coupons");
                }
            }
            else{
                freeCoupons.setVisibility(View.INVISIBLE);
                freeCouponIcon.setVisibility(View.INVISIBLE);
            }
            productPrice.setText(productPriceText);
            discountPrice.setText(discountProductPriceText);

            if(countOffersApplied > 0){
                offersApplied.setVisibility(View.VISIBLE);
                offersApplied.setText(countOffersApplied + " Offers applied");
            }
            else{
                offersApplied.setVisibility(View.INVISIBLE);
            }


        }
    }

    class CartTotalAmountViewholder extends RecyclerView.ViewHolder{

        private TextView totalItems;
        private TextView totalItemPrice;
        private TextView deliveryPrice;
        private TextView totalAmount;
        private TextView savedAmount;

        public CartTotalAmountViewholder(@NonNull View itemView) {
            super(itemView);
            totalItems = itemView.findViewById(R.id.total_items);
            totalItemPrice = itemView.findViewById(R.id.total_items_price);
            deliveryPrice = itemView.findViewById(R.id.delivery_charge_price);
            totalAmount = itemView.findViewById(R.id.total_cart_amount);
            savedAmount = itemView.findViewById(R.id.saved_amount);
        }

        private void setTotalAmount(String totalItemText, String totalItemPriceText, String deliveryPriceText, String totalAmountText, String savedAmountText){
            totalItems.setText(totalItemText);
            totalItemPrice.setText(totalItemPriceText);
            deliveryPrice.setText(deliveryPriceText);
            totalAmount.setText(totalAmountText);
            savedAmount.setText(savedAmountText);
        }
    }
}
