package com.User_food_Pannel;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.manishproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class User_Track_Fragment extends Fragment {

    RecyclerView recyclerView;
    private List<User_Final_Order> userFinalOrdersList;
    private UserTrackAdapter adapter;
    DatabaseReference databaseReference;
    TextView grandtotal, Address,Status;
    LinearLayout total;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Track");
        View v = inflater.inflate(R.layout.fragment_usertrack, null);

        recyclerView = v.findViewById(R.id.recyclefinalorders);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        grandtotal = v.findViewById(R.id.Rs);
        Address = v.findViewById(R.id.addresstrack);
        Status=v.findViewById(R.id.status);
        total = v.findViewById(R.id.btnn);
        userFinalOrdersList = new ArrayList<>();
        CustomerTrackOrder();

        return v;
    }

    private void CustomerTrackOrder() {

        databaseReference = FirebaseDatabase.getInstance().getReference("UserFinalOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userFinalOrdersList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    DatabaseReference data = FirebaseDatabase.getInstance().getReference("UserFinalOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(snapshot.getKey()).child("Dishes");
                    data.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot snapshot1 : dataSnapshot.getChildren()) {
                                User_Final_Order userFinalOrders = snapshot1.getValue(User_Final_Order.class);
                                userFinalOrdersList.add(userFinalOrders);
                            }

                            if (userFinalOrdersList.size() == 0) {
                                Address.setVisibility(View.INVISIBLE);
                                total.setVisibility(View.INVISIBLE);
                            } else {
                                Address.setVisibility(View.VISIBLE);
                                total.setVisibility(View.VISIBLE);
                            }
                            adapter = new UserTrackAdapter(getContext(), userFinalOrdersList);
                            recyclerView.setAdapter(adapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("UserFinalOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(snapshot.getKey()).child("OtherInformation");
                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            User_Final_Order1 userFinalOrders1 = dataSnapshot.getValue(User_Final_Order1.class);
                            try{
                                grandtotal.setText("₹ " + userFinalOrders1.getGrandTotalPrice());
                                Address.setText(userFinalOrders1.getAddress());
                                Status.setText(userFinalOrders1.getStatus());
                            }catch (Exception e){
                                Log.d("UserTrackFragment", "onDataChange: "+e);
                            }

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
