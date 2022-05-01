package com.example.shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddressActivity extends AppCompatActivity {

    private Button saveAddressButton;
    private EditText city;
    private EditText street;
    private EditText house;
    private EditText index;
    private EditText flat;
    private EditText note;
    private EditText name;
    private EditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        saveAddressButton = findViewById(R.id.save_address_button);
        city = findViewById(R.id.city_address);
        street = findViewById(R.id.street_address);
        house = findViewById(R.id.house_address);
        index = findViewById(R.id.index_address);
        flat = findViewById(R.id.flat_address);
        note = findViewById(R.id.note_address);
        name = findViewById(R.id.name_address);
        phone = findViewById(R.id.phone_address);

        saveAddressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("dbg", "Click");
                if(!TextUtils.isEmpty(city.getText())){
                    if(!TextUtils.isEmpty(street.getText())){
                        if(!TextUtils.isEmpty(house.getText())){
                            if(!TextUtils.isEmpty(index.getText())){
                                if(!TextUtils.isEmpty(name.getText())){
                                    if(!TextUtils.isEmpty(phone.getText()) && phone.getText().length() == 12){
                                        String address = city.getText().toString() + " " + street.getText().toString()+ " " + house.getText().toString()+ " " + flat.getText().toString();
                                        Map<String, Object> addAddress = new HashMap<>();
                                        addAddress.put("size_list", (long)(Address.addressesModelList.size()+1));
                                        addAddress.put("name_" + (long)(Address.addressesModelList.size()+1), name.getText().toString());
                                        addAddress.put("address_" + (long)(Address.addressesModelList.size()+1), address);
                                        addAddress.put("index_" + (long)(Address.addressesModelList.size()+1), index.getText().toString());
                                        addAddress.put("selected_" + (long)(Address.addressesModelList.size()+1), true);
                                        if(Address.addressesModelList.size() > 0){
                                            addAddress.put("selected_" + (long)(Address.selectedAddress+1), false);
                                        }

                                        FirebaseFirestore.getInstance().collection("USERS")
                                                .document(FirebaseAuth.getInstance().getUid())
                                                .collection("USER_DATA")
                                                .document("ADDRESSES").update(addAddress)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful()){
                                                    if(Address.addressesModelList.size() > 0) {
                                                        Address.addressesModelList.get(Address.selectedAddress).setIs_selected_address(false);
                                                    }

                                                    Address.addressesModelList.add(new AddressesModel(name.getText().toString(),
                                                            address, index.getText().toString(), true));
                                                    if(getIntent().getStringExtra("INTENT").equals("deliveryIntent")) {
                                                        Intent deliveryIntent = new Intent(AddressActivity.this, DeliveryActivity.class);
                                                        startActivity(deliveryIntent);
                                                    }
                                                    else {
                                                        MyAddressesActivity.refreshItem(Address.selectedAddress, Address.addressesModelList.size() - 1);
                                                    }
                                                    Address.selectedAddress = Cart.cartItemModelList.size() - 1;
                                                    finish();
                                                }
                                                else{
                                                    ;
                                                }
                                            }
                                        });
                                    }else{
                                        phone.requestFocus();
                                    }
                                }else {
                                    name.requestFocus();
                                }
                            }else {
                                index.requestFocus();
                            }
                        }else {
                            house.requestFocus();
                        }
                    }else{
                        street.requestFocus();
                    }
                } else{
                    city.requestFocus();
                }
                Intent deliveryIntent = new Intent(AddressActivity.this, DeliveryActivity.class);
                AddressActivity.this.startActivity(deliveryIntent);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("New address");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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