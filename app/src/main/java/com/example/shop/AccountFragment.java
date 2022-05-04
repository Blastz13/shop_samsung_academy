package com.example.shop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private List<AddressesModel> addressesModelList = new ArrayList<>();
    private int selectedAddress = 0;
    public static final int MANAGE_ADDRESS = 1;
    private Button viewAllAddressesButton;
    private Button signOutButton;
    private TextView name;
    private TextView email;
    private TextView addressName;
    private TextView address;
    private TextView index;
    private TextView orderTitle;
    private List<OrderItemModel> orderItemModelList = new ArrayList<>();
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private LinearLayout recentOrders;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);

//        NavHostFragment.findNavController(this).navigate(R.id.orderFragment);

        TextView recent_orders = view.findViewById(R.id.recent_orders);
        viewAllAddressesButton = view.findViewById(R.id.viewall_btn_address);
        signOutButton = view.findViewById(R.id.sign_out_btn);
        name = view.findViewById(R.id.name_account);
        email = view.findViewById(R.id.email_account);
        recentOrders = view.findViewById(R.id.your_recent_orders);
        addressName = view.findViewById(R.id.name_address);
        address = view.findViewById(R.id.address);
        index = view.findViewById(R.id.index_address);
        orderTitle = view.findViewById(R.id.recent_orders);

        loadAddress();
        loadUserInfo();
        loadOrders();

//        if(i<3){
//            for(int j = i; j<4; j++){
//                recentOrders.getChildAt(j).setVisibility(View.GONE);
//            }
//        }


        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent registerIntent = new Intent(getContext(), RegistrationActivity.class);
                getContext().startActivity(registerIntent);
            }
        });

        viewAllAddressesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addressesIntent = new Intent(getContext(), MyAddressesActivity.class);
                addressesIntent.putExtra("MODE", MANAGE_ADDRESS);
                getContext().startActivity(addressesIntent);
            }
        });

        recent_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.orderFragment);
            }
        });

        return view;
    }

    private void loadUserInfo(){
        firebaseFirestore.collection("USERS").document(FirebaseAuth.getInstance().getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    name.setText(task.getResult().get("name").toString());
                    email.setText(task.getResult().get("email").toString());
                }
            }
        });
    }

    private void loadAddress(){
        addressesModelList.clear();
        firebaseFirestore.collection("USERS").document(FirebaseAuth.getInstance().getUid())
                .collection("USER_DATA").document("ADDRESSES")
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                        for(long i=1; i < (long) task.getResult().get("size_list") + 1; i++){
                            addressesModelList.add(new AddressesModel(task.getResult().getBoolean("selected_"+i),
                                    task.getResult().getString("city_"+i),
                                    task.getResult().getString("street_"+i),
                                    task.getResult().getString("house_"+i),
                                    task.getResult().getString("index_"+i),
                                    task.getResult().getString("flat_"+i),
                                    task.getResult().getString("note_"+i),
                                    task.getResult().getString("name_"+i),
                                    task.getResult().getString("phone_"+i)));//////
                            if((boolean)task.getResult().get("selected_"+i)){
                                selectedAddress = (int) (i - 1);
                            }
                        }
                        if(!task.getResult().get("size_list").toString().equals("0")){
                            addressName.setText(addressesModelList.get(selectedAddress).getName());
                            address.setText(addressesModelList.get(selectedAddress).getAddress());
                            index.setText(addressesModelList.get(selectedAddress).getIndex());
                        }
                        else {
                            addressName.setText("-");
                            address.setText("-");
                            index.setText("-");
                        }
                    }
                }

        });
    }

    private void loadOrders(){
        orderItemModelList.clear();
        final int[] i = {0};
        firebaseFirestore.collection("USERS").document(FirebaseAuth.getInstance().getUid())
                .collection("USER_ORDERS").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(DocumentSnapshot documentSnapshot: task.getResult()){
                        if(documentSnapshot.get("user_id").toString().equals(FirebaseAuth.getInstance().getUid()) ) {
                            firebaseFirestore.collection("USERS").document(FirebaseAuth.getInstance()
                                    .getUid()).collection("USER_ORDERS")
                                    .document(documentSnapshot.get("order_id").toString())
                                    .collection("ORDER_ITEMS").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if(task.isSuccessful()){
                                        for(DocumentSnapshot documentSnapshot1: task.getResult()){
                                            firebaseFirestore.collection("PRODUCTS")
                                                    .document(documentSnapshot1.get("product_id").toString())
                                                    .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                    if(task.isSuccessful()){
                                                        orderTitle.setText("Your recent orders");

//                                                        for(OrderItemModel orderItemModel: Order.orderItemModelList){
                                                            if (i[0] < 4) {
                                                                Glide.with(getContext()).load(task.getResult().get("product_image_1").toString()).apply(new RequestOptions().placeholder(R.mipmap.app_ico)).into((ImageView) recentOrders.getChildAt(i[0]));
                                                                i[0]++;
                                                            }
//                                                        }
                                                        if(i[0] == 0){
                                                            orderTitle.setText("No recent orders");
                                                        }

                                                    }
                                                    else{
                                                        ;
                                                    }
                                                }
                                            });
                                        }
                                    }
                                    else{
                                        ;
                                    }
                                }
                            });
                        }
                    }
                }
                else {

                }
            }
        });
    }


}