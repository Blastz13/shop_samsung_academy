package com.example.shop;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class Rating {
    public static FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    public static List<String> RatedId = new ArrayList<>();
    public static List<Long> Rating = new ArrayList<>();

    public static void loadRating(){
        RatedId.clear();
        Rating.clear();
        firebaseFirestore.collection("USERS").document(FirebaseAuth.getInstance().getUid())
                .collection("USER_DATA").document("RATINGS")
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    Log.d("dbg", "rating");
                    for(long i=0; i < (long)task.getResult().get("size_list"); i++){
                        RatedId.add(task.getResult().get("product_id_"+ i).toString());
                        Rating.add(Long.parseLong(task.getResult().get("rating_"+ i).toString()));

                        if(task.getResult().get("product_id_"+ i).toString().equals(ProductDetailActivity.productId) && ProductDetailActivity.rateNowLayoutContainer != null){
                            ProductDetailActivity.InitRating = Integer.parseInt(task.getResult().get("rating_"+ i).toString())-1;
                            ProductDetailActivity.setRating(ProductDetailActivity.InitRating);
                        }
                    }
                }
                else{
;
                }
            }
        });
    }
}
