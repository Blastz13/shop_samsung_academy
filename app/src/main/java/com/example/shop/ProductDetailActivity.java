package com.example.shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailActivity extends AppCompatActivity {

    private ViewPager productImagesViewPager;
    private TabLayout viewPagerIndicator;
    private FloatingActionButton addToWishListBtn;
    private static boolean isAddedToWishList = false;
    private ViewPager productDetailViewPager;
    private TabLayout productDetailTabLayout;
    private LinearLayout rateNowLayoutContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        productImagesViewPager = findViewById(R.id.product_images_viewpager);
        viewPagerIndicator = findViewById(R.id.view_pager_indicator);
        addToWishListBtn = findViewById(R.id.add_to_wish_btn);
        productDetailViewPager = findViewById(R.id.product_detail_viewpager_tab);
        productDetailTabLayout = findViewById(R.id.product_detail_tablayout);


        List<Integer> productImages = new ArrayList<>();
        productImages.add(R.drawable.product_item);
        productImages.add(R.drawable.product_item_1);
        productImages.add(R.drawable.product_item);
        productImages.add(R.drawable.product_item_1);

        ProductImagesAdapter productImagesAdapter = new ProductImagesAdapter(productImages);
        productImagesViewPager.setAdapter(productImagesAdapter);
        viewPagerIndicator.setupWithViewPager(productImagesViewPager, true);

        addToWishListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAddedToWishList){
                    isAddedToWishList = false;
                    addToWishListBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#9e9e9e")));
                }
                else{
                    isAddedToWishList = true;
                    addToWishListBtn.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
                }
            }
        });

        ProductDetailAdapter productDetailAdapter = new ProductDetailAdapter(getSupportFragmentManager(), productDetailTabLayout.getTabCount());
        productDetailViewPager.setAdapter(productDetailAdapter);
        productDetailViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(productDetailTabLayout));
        productDetailTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                productDetailViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        rateNowLayoutContainer = findViewById(R.id.rate_now_container);
        for(int i = 0; i < rateNowLayoutContainer.getChildCount(); i++){
            final int starPosition = i;
            rateNowLayoutContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setRating(starPosition);
                }
            });
        }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_cart_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
            return true;
        }
        else if (id == R.id.main_search_icon){
            return true;
        }
        else if (id == R.id.main_cart_icon){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}