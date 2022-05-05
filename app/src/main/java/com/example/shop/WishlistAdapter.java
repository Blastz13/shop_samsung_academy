package com.example.shop;

import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;
import java.util.stream.Stream;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.ViewHolder> {

    private List<WishlistModel> wishlistModelList;
    private Boolean isWishList;

    public WishlistAdapter(List<WishlistModel> wishlistModelList, boolean isWishList) {
        this.wishlistModelList = wishlistModelList;
        this.isWishList = isWishList;
    }

    @NonNull
    @Override
    public WishlistAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WishlistAdapter.ViewHolder holder, int position) {
        String productId = wishlistModelList.get(position).getProductId();
        String resource = wishlistModelList.get(position).getProductImage();
        String title = wishlistModelList.get(position).getProductTitle();
        long freeCoupon = wishlistModelList.get(position).getFreeCoupons();
        String rating = wishlistModelList.get(position).getRating();
        long totalRating = wishlistModelList.get(position).getTotalRating();
        String productPrice = wishlistModelList.get(position).getProductPrice();
        String discountPrice = wishlistModelList.get(position).getProductDiscountPrice();
        holder.setData(productId, resource, title, freeCoupon, rating, totalRating, productPrice, discountPrice);
    }

    @Override
    public int getItemCount() {
        return wishlistModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView productImage;
        private ImageView couponIcon;
        private TextView productTitle;
        private TextView freeCoupon;
        private TextView productPrice;
        private TextView productDiscountPrice;
        private TextView paymentMethod;
        private TextView rating;
        private TextView totalRating;
        private View priceDivider;
        private ImageView removeButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image_wishlist);
            productTitle = itemView.findViewById(R.id.product_title_wishlist);
//            couponIcon = itemView.findViewById(R.id.coupon_icon_wishlist);
//            freeCoupon = itemView.findViewById(R.id.free_coupon_wishlist);
            productPrice = itemView.findViewById(R.id.product_price_wishlist);
            productDiscountPrice = itemView.findViewById(R.id.product_discount_price_wishlist);
            paymentMethod = itemView.findViewById(R.id.payment_method_wishlist);
            rating = itemView.findViewById(R.id.product_rating_preview);
            totalRating = itemView.findViewById(R.id.total_rating_wishlist);
            priceDivider = itemView.findViewById(R.id.price_divider_wishlist);
            removeButton = itemView.findViewById(R.id.remove_product_wishlist);
        }

        private void setData(String productId, String resource, String title, long freeCouponsNumber, String averageRate, long totalRatingsNumber, String price, String discountPrice){
            Glide.with(itemView.getContext()).load(resource).apply(new RequestOptions().placeholder(R.drawable.ic_home)).into(productImage);
            productTitle.setText(title);
//            if (freeCouponsNumber != 0){
//                couponIcon.setVisibility(View.VISIBLE);
//                if(freeCouponsNumber == 1){
//                    freeCoupon.setText("free " + freeCouponsNumber + " coupon");
//                }
//                else{
//                    freeCoupon.setText("free " + freeCouponsNumber + " coupons");
//                }
//            }
//            else{
//                couponIcon.setVisibility(View.INVISIBLE);
//                freeCoupon.setVisibility(View.INVISIBLE);
//            }
            rating.setText(averageRate);
            totalRating.setText(totalRatingsNumber + " ratings");
            if(discountPrice.equals("0")){
                productPrice.setText(price + " $");
                productDiscountPrice.setVisibility(View.GONE);

            }
            else {
                productPrice.setText(price+ " $");
                productDiscountPrice.setText(discountPrice + " $");
            }

            if(isWishList){
                removeButton.setVisibility(View.VISIBLE);
            }
            else{
                removeButton.setVisibility(View.GONE);
            }
            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = WishFragment.wl.indexOf(productId);
                    WishFragment.removeWishList(index);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent productDetailIntent = new Intent(itemView.getContext(), ProductDetailActivity.class);
                    productDetailIntent.putExtra("product_id", productId);
                    itemView.getContext().startActivity(productDetailIntent);
                }
            });
        }
    }
}
