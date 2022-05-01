package com.example.shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DeliveryActivity extends AppCompatActivity {

    public static List<CartItemModel> cartItemModelList;
    private RecyclerView deliveryItemsRecyclerView;
    private Button changeOrAddAddressButton;
    private Button continueButton;
    private TextView totalAmount;
    private TextView name;
    private TextView address;
    private TextView index;
    private TextView continueShopping;
    private TextView orderId;
    private ConstraintLayout orderConfirmLayout;
    private Dialog payment;
    Button payment_visa;
    Button payment_pp;
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
        totalAmount = findViewById(R.id.total_cart_amount);
        changeOrAddAddressButton = findViewById(R.id.change_or_add_address_btn);
        name = findViewById(R.id.fullname_order);
        address = findViewById(R.id.address_detail);
        index = findViewById(R.id.index_address_order);
        orderConfirmLayout = findViewById(R.id.order_confirm_layout);
        continueShopping = findViewById(R.id.continue_shopping_btn);
        orderId = findViewById(R.id.order_id);

        payment = new Dialog(DeliveryActivity.this);
        payment.setContentView(R.layout.payment);
        payment.setCancelable(false);
        payment.getWindow().setBackgroundDrawable(getDrawable(R.drawable.slider_background));
        payment.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        continueButton = findViewById(R.id.cart_continue_btn);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payment.show();
                payment_visa = payment.findViewById(R.id.payment_visa);
                payment_pp = payment.findViewById(R.id.payment_pp);

                payment_pp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        payment.dismiss();
                        orderConfirmLayout.setVisibility(View.VISIBLE);
                        continueShopping.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finish();
                            }
                        });
                    }
                });

                payment_visa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        payment.dismiss();
                        orderConfirmLayout.setVisibility(View.VISIBLE);
                        continueShopping.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finish();
                            }
                        });

                    }
                });

            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        deliveryItemsRecyclerView.setLayoutManager(layoutManager);
//        List<CartItemModel> cartItemModelList = new ArrayList<>();
//        cartItemModelList.add(new CartItemModel(0, R.drawable.product_item, "NoteBook", 2, "500$", "200$", 1,0,0));
//        cartItemModelList.add(new CartItemModel(0, R.drawable.product_item_1, "NoteBook", 2, "500$", "200$", 1,1,0));
//        cartItemModelList.add(new CartItemModel(0, R.drawable.product_item, "NoteBook", 2, "500$", "200$", 1,1,0));
//        cartItemModelList.add(new CartItemModel(0, R.drawable.product_item_1, "NoteBook", 2, "500$", "200$", 1,1,0));
//        cartItemModelList.add(new CartItemModel(1, "Price", "10000$", "Free", "10000$", "You saved 10$"));

        CartAdapter cartAdapter = new CartAdapter(cartItemModelList, totalAmount, false);
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

        name.setText(Address.addressesModelList.get(Address.selectedAddress).getName());
        address.setText(Address.addressesModelList.get(Address.selectedAddress).getAddress());
        index.setText(Address.addressesModelList.get(Address.selectedAddress).getIndex());
    }

    @Override
    protected void onStart() {
        super.onStart();
        name.setText(Address.addressesModelList.get(Address.selectedAddress).getName());
        address.setText(Address.addressesModelList.get(Address.selectedAddress).getAddress());
        index.setText(Address.addressesModelList.get(Address.selectedAddress).getIndex());
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