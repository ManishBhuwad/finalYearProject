package com.example.manishproject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mess_food_pannel.MessFinalOrders;
import com.mess_food_pannel.MessFinalOrders1;
import com.mess_food_pannel.MessPreparedOrderViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MessPreparedOrderView extends Activity {
    RecyclerView recyclerViewdish;
    private List<MessFinalOrders> messFinalOrdersList;
    private MessPreparedOrderViewAdapter adapter;
    DatabaseReference reference;
    String RandomUID, userid;
    TextView grandtotal, address, name, number;
    LinearLayout l1;
    Button Prepared;
    private ProgressDialog progressDialog;
    //    private APIService apiService;
    Spinner Shipper;
    String deliveryId = "oCpc4SwLVFbKO0fPdtp4R6bmDmI3";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_prepared_order_view);

        recyclerViewdish = findViewById(R.id.Recycle_viewOrder);
        grandtotal = findViewById(R.id.Gtotal);
        address = findViewById(R.id.Cadress);
        name = findViewById(R.id.Cname);
        number = findViewById(R.id.Cnumber);
        l1 = findViewById(R.id.linear);
        Shipper = findViewById(R.id.shipper);
        Prepared = findViewById(R.id.prepared);
//        apiService = Client.getClient("https://fcm.googleapis.com/").create(APIService.class);
        progressDialog = new ProgressDialog(MessPreparedOrderView.this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        recyclerViewdish.setHasFixedSize(true);
        recyclerViewdish.setLayoutManager(new LinearLayoutManager(MessPreparedOrderView.this));
        messFinalOrdersList = new ArrayList<>();
        MessorderdishesView();
    }

    private void MessorderdishesView() {

        RandomUID = getIntent().getStringExtra("RandomUID");

        reference = FirebaseDatabase.getInstance().getReference("MessFinalOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUID).child("Dishes");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                messFinalOrdersList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    MessFinalOrders messFinalOrders = snapshot.getValue(MessFinalOrders.class);
                    messFinalOrdersList.add(messFinalOrders);
                }
                if (messFinalOrdersList.size() == 0) {
                    l1.setVisibility(View.INVISIBLE);

                } else {
                    l1.setVisibility(View.VISIBLE);
                    Prepared.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            progressDialog.setMessage("Please wait...");
                            progressDialog.show();

                            DatabaseReference data = FirebaseDatabase.getInstance().getReference("Mess").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                            data.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    final Mess mess = dataSnapshot.getValue(Mess.class);
                                    final String MessName = mess.getFname() + " " + mess.getLname();
                                    DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("MessFinalOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUID).child("Dishes");
                                    databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                                final MessFinalOrders messFinalOrders = dataSnapshot1.getValue(MessFinalOrders.class);
                                                HashMap<String, String> hashMap = new HashMap<>();
                                                String dishid = messFinalOrders.getDishId();
                                                userid = messFinalOrders.getUserId();
                                                hashMap.put("MessId", messFinalOrders.getMessId());
                                                hashMap.put("DishId", messFinalOrders.getDishId());
                                                hashMap.put("DishName", messFinalOrders.getDishName());
                                                hashMap.put("DishPrice", messFinalOrders.getDishPrice());
                                                hashMap.put("DishQuantity", messFinalOrders.getDishQuantity());
                                                hashMap.put("RandomUID", RandomUID);
                                                hashMap.put("TotalPrice", messFinalOrders.getTotalPrice());
                                                hashMap.put("UserId", messFinalOrders.getUserId());
                                                FirebaseDatabase.getInstance().getReference("DeliveryShipOrders").child(deliveryId).child(RandomUID).child("Dishes").child(dishid).setValue(hashMap);
                                            }
                                            DatabaseReference data = FirebaseDatabase.getInstance().getReference("MessFinalOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUID).child("OtherInformation");
                                            data.addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    final MessFinalOrders1 messFinalOrders1 = dataSnapshot.getValue(MessFinalOrders1.class);
                                                    HashMap<String, String> hashMap1 = new HashMap<>();
                                                    String messid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                                    hashMap1.put("Address", messFinalOrders1.getAddress());
                                                    hashMap1.put("MessId", messid);
                                                    hashMap1.put("MessName", MessName);
                                                    hashMap1.put("GrandTotalPrice", messFinalOrders1.getGrandTotalPrice());
                                                    hashMap1.put("MobileNumber", messFinalOrders1.getMobileNumber());
                                                    hashMap1.put("Name", messFinalOrders1.getName());
                                                    hashMap1.put("RandomUID", RandomUID);
                                                    hashMap1.put("Status", "Order is Prepared");
                                                    hashMap1.put("UserId", userid);
                                                    FirebaseDatabase.getInstance().getReference("DeliveryShipOrders").child(deliveryId).child(RandomUID).child("OtherInformation").setValue(hashMap1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            FirebaseDatabase.getInstance().getReference("CustomerFinalOrders").child(userid).child(RandomUID).child("OtherInformation").child("Status").setValue("Order is Prepared...").addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                @Override
                                                                public void onSuccess(Void aVoid) {
                                                                    FirebaseDatabase.getInstance().getReference().child("Tokens").child(userid).child("token").addListenerForSingleValueEvent(new ValueEventListener() {
                                                                        @Override
                                                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                            String usertoken = dataSnapshot.getValue(String.class);
//                                                                            sendNotifications(usertoken, "Estimated Time", "Your Order is Prepared", "Prepared");
                                                                        }

                                                                        @Override
                                                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                                                        }
                                                                    });
                                                                }
                                                            }).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                @Override
                                                                public void onSuccess(Void aVoid) {
                                                                    FirebaseDatabase.getInstance().getReference().child("Tokens").child(deliveryId).child("token").addListenerForSingleValueEvent(new ValueEventListener() {
                                                                        @Override
                                                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                            String usertoken = dataSnapshot.getValue(String.class);
//                                                                            sendNotifications(usertoken, "New Order", "You have a New Order", "DeliveryOrder");
                                                                            progressDialog.dismiss();
                                                                            AlertDialog.Builder builder = new AlertDialog.Builder(MessPreparedOrderView.this);
                                                                            builder.setMessage("Order has been sent to shipper");
                                                                            builder.setCancelable(false);
                                                                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                                @Override
                                                                                public void onClick(DialogInterface dialog, int which) {

                                                                                    dialog.dismiss();
                                                                                    Intent b = new Intent(MessPreparedOrderView.this, MessPreparedOrder.class);
                                                                                    startActivity(b);
                                                                                    finish();


                                                                                }
                                                                            });
                                                                            AlertDialog alert = builder.create();
                                                                            alert.show();
                                                                        }

                                                                        @Override
                                                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                                                        }
                                                                    });
                                                                }
                                                            });


                                                        }
                                                    });
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                        }
                    });

                }
                adapter = new MessPreparedOrderViewAdapter(MessPreparedOrderView.this, messFinalOrdersList);
                recyclerViewdish.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("MessFinalOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUID).child("OtherInformation");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                MessFinalOrders1 messFinalOrders1 = dataSnapshot.getValue(MessFinalOrders1.class);
                grandtotal.setText("₹ " + messFinalOrders1.getGrandTotalPrice());
                address.setText(messFinalOrders1.getAddress());
                name.setText(messFinalOrders1.getName());
                number.setText("+91" + messFinalOrders1.getMobileNumber());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

//    private void sendNotifications(String usertoken, String title, String message, String prepared) {
//
//        Data data = new Data(title, message, prepared);
//        NotificationSender sender = new NotificationSender(data, usertoken);
//        apiService.sendNotification(sender).enqueue(new Callback<MyResponse>() {
//            @Override
//            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
//                if (response.code() == 200) {
//                    if (response.body().success != 1) {
//                        Toast.makeText(MessPreparedOrderView.this, "Failed", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<MyResponse> call, Throwable t) {
//
//            }
//        });
//    }
}
