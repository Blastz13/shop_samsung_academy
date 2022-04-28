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
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class DeliveryActivity extends AppCompatActivity {

    private RecyclerView deliveryItemsRecyclerView;
    private Button changeOrAddAddressButton;
    public static final int SELECT_ADDRESS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Delivery");

        deliveryItemsRecyclerView = findViewById(R.id.delivery_recyclerview);
        changeOrAddAddressButton = findViewById(R.id.change_or_add_address_btn);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        deliveryItemsRecyclerView.setLayoutManager(layoutManager);
        List<CartItemModel> cartItemModelList = new ArrayList<>();
//        cartItemModelList.add(new CartItemModel(0, R.drawable.product_item, "NoteBook", 2, "500$", "200$", 1,0,0));
//        cartItemModelList.add(new CartItemModel(0, R.drawable.product_item_1, "NoteBook", 2, "500$", "200$", 1,1,0));
//        cartItemModelList.add(new CartItemModel(0, R.drawable.product_item, "NoteBook", 2, "500$", "200$", 1,1,0));
//        cartItemModelList.add(new CartItemModel(0, R.drawable.product_item_1, "NoteBook", 2, "500$", "200$", 1,1,0));
//        cartItemModelList.add(new CartItemModel(1, "Price", "10000$", "Free", "10000$", "You saved 10$"));

        CartAdapter cartAdapter = new CartAdapter(cartItemModelList);
        deliveryItemsRecyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

        changeOrAddAddressButton.setVisibility(View.VISIBLE);
        changeOrAddAddressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addressesIntent = new Intent(DeliveryActivity.this, MyAddressesActivity.class);
                addressesIntent.putExtra("MODE", SELECT_ADDRESS);
                DeliveryActivity.this.startActivity(addressesIntent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}