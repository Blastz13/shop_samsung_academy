package com.example.shop;

import static com.example.shop.DeliveryActivity.SELECT_ADDRESS;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MyAddressesActivity extends AppCompatActivity {

    private RecyclerView addressesRecyclerView;
    private Button deliveryButton;
    private static AddressesAdapter addressesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_addresses);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("My addresses");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addressesRecyclerView = findViewById(R.id.addresses_recyclerview);
        deliveryButton = findViewById(R.id.dev_btn);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        addressesRecyclerView.setLayoutManager(layoutManager);

        List<AddressesModel> addressesModelList = new ArrayList<>();
        addressesModelList.add(new AddressesModel("Petr", "New York h. 12", "324122", true));
        addressesModelList.add(new AddressesModel("Petr", "New York h. 12", "324122", false));
        addressesModelList.add(new AddressesModel("Petr", "New York h. 12", "324122", false));

        if( getIntent().getIntExtra("MODE", -1) == SELECT_ADDRESS){
            deliveryButton.setVisibility(View.VISIBLE);
        }
        else{
            deliveryButton.setVisibility(View.GONE);
        }

        addressesAdapter = new AddressesAdapter(addressesModelList, getIntent().getIntExtra("MODE", -1));
        addressesRecyclerView.setAdapter(addressesAdapter);
        ((SimpleItemAnimator)addressesRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        addressesAdapter.notifyDataSetChanged();
    }

    public static void refreshItem(int preSelect, int select){
        addressesAdapter.notifyItemChanged(preSelect);
        addressesAdapter.notifyItemChanged(select);
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