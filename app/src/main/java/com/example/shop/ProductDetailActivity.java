package com.example.shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

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
    private FirebaseFirestore firebaseFirestore;
    private TextView productTitle;
    private TextView productRatingPreview;
    private TextView productTotalRating;
    private TextView productPrice;
    private TextView productDiscountPrice;
    private TextView productAvailable;
    private TextView productTotalRatings;
    private TextView productRatingMark1;
    private TextView productRatingMark2;
    private TextView productRatingMark3;
    private TextView productRatingMark4;
    private TextView productRatingMark5;
    private TextView totalRatingFigure;
    private TextView averageRating;
    private ProgressBar progressBarMark1;
    private ProgressBar progressBarMark2;
    private ProgressBar progressBarMark3;
    private ProgressBar progressBarMark4;
    private ProgressBar progressBarMark5;

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
        productTitle = findViewById(R.id.product_title);
        productRatingPreview = findViewById(R.id.product_rating_preview);
        productTotalRating = findViewById(R.id.total_rating);
        productTotalRatings = findViewById(R.id.total_ratings);
        productPrice = findViewById(R.id.product_price);
        productDiscountPrice = findViewById(R.id.product_discount_price);
        productAvailable = findViewById(R.id.product_available);
        productRatingMark1 = findViewById(R.id.product_rating_mark_1);
        productRatingMark2 = findViewById(R.id.product_rating_mark_2);
        productRatingMark3 = findViewById(R.id.product_rating_mark_3);
        productRatingMark4 = findViewById(R.id.product_rating_mark_4);
        productRatingMark5 = findViewById(R.id.product_rating_mark_5);
        totalRatingFigure = findViewById(R.id.total_rating_figure);
        progressBarMark1 = findViewById(R.id.progressBar_mark_1);
        progressBarMark2 = findViewById(R.id.progressBar_mark_2);
        progressBarMark3 = findViewById(R.id.progressBar_mark_3);
        progressBarMark4 = findViewById(R.id.progressBar_mark_4);
        progressBarMark5 = findViewById(R.id.progressBar_mark_5);
        averageRating = findViewById(R.id.average_rating);

        firebaseFirestore = FirebaseFirestore.getInstance();

        List<String> productImages = new ArrayList<>();

        ProductImagesAdapter productImagesAdapter = new ProductImagesAdapter(productImages);

                firebaseFirestore.collection("PRODUCTS").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot documentSnapshot: task.getResult()) {
                                if(documentSnapshot.get("product_id").toString().equals(getIntent().getStringExtra("product_id"))) {
                                    for (int i = 1; i <= (long) documentSnapshot.get("count_image_product"); i++) {
                                        productImages.add(documentSnapshot.get("product_image_" + i).toString());
                                    }
                                    productTitle.setText(documentSnapshot.get("product_title").toString());
                                    productRatingPreview.setText(documentSnapshot.get("avg_rating").toString());
                                    averageRating.setText(documentSnapshot.get("avg_rating").toString());
                                    productTotalRating.setText(documentSnapshot.get("total_rating").toString());
                                    productPrice.setText(documentSnapshot.get("product_price").toString());
                                    productDiscountPrice.setText(documentSnapshot.get("product_discount_price").toString());
                                    productAvailable.setText(documentSnapshot.get("product_available").toString());
                                    productTotalRatings.setText(documentSnapshot.get("total_rating").toString());

                                    productRatingMark1.setText(documentSnapshot.get("mark_1").toString());
                                    productRatingMark2.setText(documentSnapshot.get("mark_2").toString());
                                    productRatingMark3.setText(documentSnapshot.get("mark_3").toString());
                                    productRatingMark4.setText(documentSnapshot.get("mark_4").toString());
                                    productRatingMark5.setText(documentSnapshot.get("mark_5").toString());
                                    totalRatingFigure.setText(documentSnapshot.get("total_rating").toString());

                                    progressBarMark1.setMax(Integer.parseInt(documentSnapshot.get("total_rating").toString()));
                                    progressBarMark1.setProgress(Integer.parseInt(documentSnapshot.get("mark_1").toString()));
                                    progressBarMark2.setMax(Integer.parseInt(documentSnapshot.get("total_rating").toString()));
                                    progressBarMark2.setProgress(Integer.parseInt(documentSnapshot.get("mark_2").toString()));
                                    progressBarMark3.setMax(Integer.parseInt(documentSnapshot.get("total_rating").toString()));
                                    progressBarMark3.setProgress(Integer.parseInt(documentSnapshot.get("mark_3").toString()));
                                    progressBarMark4.setMax(Integer.parseInt(documentSnapshot.get("total_rating").toString()));
                                    progressBarMark4.setProgress(Integer.parseInt(documentSnapshot.get("mark_4").toString()));
                                    progressBarMark5.setMax(Integer.parseInt(documentSnapshot.get("total_rating").toString()));
                                    progressBarMark5.setProgress(Integer.parseInt(documentSnapshot.get("mark_5").toString()));

                                    productImagesAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                        else{
                            ;
                        }
                    }
                });

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
            Intent mainIntent = new Intent(this, MainActivity.class);
            mainIntent.putExtra("is_cart", true);
            this.startActivity(mainIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}