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
import com.mess_food_pannel.MessFinalOrders1;
import com.mess_food_pannel.MessPreparedOrderAdapter;

import java.util.ArrayList;
import java.util.List;

public class MessPreparedOrder extends Activity {
    private RecyclerView recyclerView;
    private List<MessFinalOrders1> messFinalOrders1List;
    private MessPreparedOrderAdapter adapter;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_prepared_order);
        recyclerView = findViewById(R.id.Recycle_preparedOrders);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MessPreparedOrder.this));
        messFinalOrders1List = new ArrayList<>();
        MessPrepareOrders();
    }
    private void MessPrepareOrders() {

        databaseReference = FirebaseDatabase.getInstance().getReference("MessFinalOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                messFinalOrders1List.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    DatabaseReference data = FirebaseDatabase.getInstance().getReference("MessFinalOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(snapshot.getKey()).child("OtherInformation");
                    data.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            MessFinalOrders1 messFinalOrders1 = dataSnapshot.getValue(MessFinalOrders1.class);
                            messFinalOrders1List.add(messFinalOrders1);
                            adapter = new MessPreparedOrderAdapter(MessPreparedOrder.this, messFinalOrders1List);
                            recyclerView.setAdapter(adapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
