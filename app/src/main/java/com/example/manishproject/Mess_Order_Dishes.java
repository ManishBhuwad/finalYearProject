package com.example.manishproject;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mess_food_pannel.MessOrderDishesAdapter;
import com.mess_food_pannel.MessPendingOrders;

import java.util.ArrayList;
import java.util.List;

public class Mess_Order_Dishes extends Activity {

    RecyclerView recyclerViewdish;
    private List<MessPendingOrders> messPendingOrdersList;
    private MessOrderDishesAdapter adapter;
    DatabaseReference reference;
    String RandomUID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_order_dishes);
        recyclerViewdish = findViewById(R.id.Recycle_orders_dish);
        recyclerViewdish.setHasFixedSize(true);
        recyclerViewdish.setLayoutManager(new LinearLayoutManager(this));
        messPendingOrdersList = new ArrayList<>();
        Messorderdishes();
    }
    private void Messorderdishes() {

        RandomUID = getIntent().getStringExtra("RandomUID");

        reference = FirebaseDatabase.getInstance().getReference("MessPendingOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUID).child("Dishes");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                messPendingOrdersList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    MessPendingOrders messPendingOrders = snapshot.getValue(MessPendingOrders.class);
                    messPendingOrdersList.add(messPendingOrders);
                }
                adapter = new MessOrderDishesAdapter(Mess_Order_Dishes.this,messPendingOrdersList);
                recyclerViewdish.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
