package com.example.shop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<CategoryModel> categoryModelList;

    public CategoryAdapter(List<CategoryModel> categoryModelList) {
        this.categoryModelList = categoryModelList;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        String ico = categoryModelList.get(position).getCategoryIco();
        String title = categoryModelList.get(position).getCategoryTitle();

        holder.setCategoryTitle(title);
    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView categoryIco;
        private TextView categoryTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryIco = itemView.findViewById(R.id.category_ico);
            categoryTitle = itemView.findViewById(R.id.category_title);
        }

        private void setCategoryIco(){

        }

        private void setCategoryTitle(String title){
            categoryTitle.setText(title);
        }
    }
}
