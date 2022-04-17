package com.example.shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class AllProductsActivity extends AppCompatActivity {

    private RecyclerView allProductsRecyclerView;
    private GridView allProductsGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_products);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        allProductsRecyclerView = findViewById(R.id.all_products_recyclerview);
        allProductsGridView = findViewById(R.id.all_products_gridview);

        int type_layout = getIntent().getIntExtra("type_layout", -1);

        if(type_layout == 0){

            allProductsRecyclerView.setVisibility(View.VISIBLE);

            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            allProductsRecyclerView.setLayoutManager(layoutManager);

            List<WishlistModel> wishlistModelList = new ArrayList<>();
            wishlistModelList.add(new WishlistModel(R.drawable.product_item_1, 0, 5, "Macbook", "22", "90$", "88$", "no"));
            wishlistModelList.add(new WishlistModel(R.drawable.product_item_1, 2, 5, "Macbook", "22", "90$", "88$", "no"));
            wishlistModelList.add(new WishlistModel(R.drawable.product_item_1, 1, 5, "Macbook", "22", "90$", "88$", "no"));
            wishlistModelList.add(new WishlistModel(R.drawable.product_item_1, 4, 5, "Macbook", "22", "90$", "88$", "no"));
            wishlistModelList.add(new WishlistModel(R.drawable.product_item_1, 4, 5, "Macbook", "22", "90$", "88$", "no"));
            wishlistModelList.add(new WishlistModel(R.drawable.product_item_1, 4, 5, "Macbook", "22", "90$", "88$", "no"));
            wishlistModelList.add(new WishlistModel(R.drawable.product_item_1, 4, 5, "Macbook", "22", "90$", "88$", "no"));

            WishlistAdapter wishlistAdapter = new WishlistAdapter(wishlistModelList, false);
            allProductsRecyclerView.setAdapter(wishlistAdapter);
            wishlistAdapter.notifyDataSetChanged();
        }
        else if (type_layout == 1){

            allProductsGridView.setVisibility(View.VISIBLE);

            List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.product_item, "NoteBook", "700 $"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.product_item_1, "Personal Computer", "200 $"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.product_item_1, "Phone", "1700 $"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.product_item, "Watch", "7300 $"));

            GridProductLayoutAdapter gridProductLayoutAdapter = new GridProductLayoutAdapter(horizontalProductScrollModelList);
            allProductsGridView.setAdapter(gridProductLayoutAdapter);
            gridProductLayoutAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}