package com.example.shop;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.Viewholder> {

    private List<OrderItemModel> orderItemModelList;

    public OrderAdapter(List<OrderItemModel> orderItemModelList) {
        this.orderItemModelList = orderItemModelList;
    }

    @NonNull
    @Override
    public OrderAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_order_item_layout, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.Viewholder holder, int position) {
        int resource = orderItemModelList.get(position).getProductImage();
        int rating = orderItemModelList.get(position).getRating();
        String title = orderItemModelList.get(position).getProductTitle();
        String deliveredDate = orderItemModelList.get(position).getDeliveryStatus();
        holder.setData(resource, title, deliveredDate, rating);

    }

    @Override
    public int getItemCount() {
        return orderItemModelList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        private ImageView productImage;
        private ImageView deliveryIndicator;
        private TextView productTitle;
        private TextView deliveryStatus;
        private LinearLayout rateNowLayoutContainer;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.order_product_image);
            productTitle = itemView.findViewById(R.id.order_product_title);
            deliveryIndicator = itemView.findViewById(R.id.indicator_status_order);
            deliveryStatus = itemView.findViewById(R.id.delivared_order_date);
            rateNowLayoutContainer = itemView.findViewById(R.id.rate_now_container);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent orderDetailIntent = new Intent(itemView.getContext(), OrderDetailActivity.class);
                    itemView.getContext().startActivity(orderDetailIntent);
                }
            });
        }
        private void setData(int resource, String title, String deliveryDate, int rating){
            productImage.setImageResource(resource);
            productTitle.setText(title);
            if(deliveryDate.equals("Cancelled")) {
                deliveryIndicator.setImageTintList(ColorStateList.valueOf(itemView.getContext().getResources().getColor(R.color.red)));
            }
            else{
                deliveryIndicator.setImageTintList(ColorStateList.valueOf(itemView.getContext().getResources().getColor(R.color.green)));
            }
            setRating(rating);
            for(int i = 0; i < rateNowLayoutContainer.getChildCount(); i++){
                final int starPosition = i;
                rateNowLayoutContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setRating(starPosition);
                    }
                });
            }

            deliveryStatus.setText(deliveryDate);
        }
        private void setRating(int starPosition){
            for(int i = 0; i < rateNowLayoutContainer.getChildCount(); i++){
                ImageView starBtn = (ImageView) rateNowLayoutContainer.getChildAt(i);
                starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#bebebe")));
                if(i<= starPosition){
                    starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffbb00")));
                }
            }
        }
    }
}
