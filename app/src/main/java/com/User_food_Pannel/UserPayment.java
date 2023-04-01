package com.User_food_Pannel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manishproject.R;
import com.example.manishproject.User_FoodPannel_BottomNavigation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class UserPayment extends AppCompatActivity {

    TextView OnlinePayment, CashPayment;
    String RandomUID, MessID;
    //private APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_payment);

        OnlinePayment = (TextView) findViewById(R.id.online);
        CashPayment = (TextView) findViewById(R.id.cash);
        RandomUID = getIntent().getStringExtra("RandomUID");
        //apiService = Client.getClient("https://fcm.googleapis.com/").create(APIService.class);

        OnlinePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserPayment.this, UserOnlinePayment.class);
                intent.putExtra("randomUID", RandomUID);
                startActivity(intent);
            }
        });


        CashPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("UserPaymentOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUID).child("Dishes");
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            final UserPaymentOrders userPaymentOrders = dataSnapshot1.getValue(UserPaymentOrders.class);
                            HashMap<String, String> hashMap = new HashMap<>();
                            String dishid = userPaymentOrders.getDishId();
                            hashMap.put("MessId", userPaymentOrders.getMessId());
                            hashMap.put("DishId", userPaymentOrders.getDishId());
                            hashMap.put("DishName", userPaymentOrders.getDishName());
                            hashMap.put("DishPrice", userPaymentOrders.getDishPrice());
                            hashMap.put("DishQuantity", userPaymentOrders.getDishQuantity());
                            hashMap.put("RandomUID", RandomUID);
                            hashMap.put("TotalPrice", userPaymentOrders.getTotalPrice());
                            hashMap.put("UserId", userPaymentOrders.getUserId());
                            FirebaseDatabase.getInstance().getReference("UserFinalOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUID).child("Dishes").child(dishid).setValue(hashMap);
                        }
                        DatabaseReference data = FirebaseDatabase.getInstance().getReference("UserPaymentOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUID).child("OtherInformation");
                        data.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                final UserPaymentOrders1 userPaymentOrders1 = dataSnapshot.getValue(UserPaymentOrders1.class);
                                HashMap<String, String> hashMap1 = new HashMap<>();
                                hashMap1.put("Address", userPaymentOrders1.getAddress());
                                hashMap1.put("GrandTotalPrice", userPaymentOrders1.getGrandTotalPrice());
                                hashMap1.put("MobileNumber", userPaymentOrders1.getMobileNumber());
                                hashMap1.put("Name", userPaymentOrders1.getName());
                                hashMap1.put("Note", userPaymentOrders1.getNote());
                                hashMap1.put("RandomUID", RandomUID);
                                hashMap1.put("Status", "Your order is waiting to be prepared by Chef...");
                                FirebaseDatabase.getInstance().getReference("UserFinalOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUID).child("OtherInformation").setValue(hashMap1).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        DatabaseReference Reference = FirebaseDatabase.getInstance().getReference("UserPaymentOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUID).child("Dishes");
                                        Reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                                    final UserPaymentOrders UserPaymentOrderss = snapshot.getValue(UserPaymentOrders.class);
                                                    HashMap<String, String> hashMap2 = new HashMap<>();
                                                    String dishid = UserPaymentOrderss.getDishId();
                                                    MessID = UserPaymentOrderss.getMessId();
                                                    hashMap2.put("MessId", UserPaymentOrderss.getMessId());
                                                    hashMap2.put("DishId", UserPaymentOrderss.getDishId());
                                                    hashMap2.put("DishName", UserPaymentOrderss.getDishName());
                                                    hashMap2.put("DishPrice", UserPaymentOrderss.getDishPrice());
                                                    hashMap2.put("DishQuantity", UserPaymentOrderss.getDishQuantity());
                                                    hashMap2.put("RandomUID", RandomUID);
                                                    hashMap2.put("TotalPrice", UserPaymentOrderss.getTotalPrice());
                                                    hashMap2.put("UserId", UserPaymentOrderss.getUserId());
                                                    FirebaseDatabase.getInstance().getReference("MessWaitingOrders").child(MessID).child(RandomUID).child("Dishes").child(dishid).setValue(hashMap2);
                                                }
                                                DatabaseReference dataa = FirebaseDatabase.getInstance().getReference("UserPaymentOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUID).child("OtherInformation");
                                                dataa.addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                        UserPaymentOrders1 userPaymentOrders11 = dataSnapshot.getValue(UserPaymentOrders1.class);
                                                        HashMap<String, String> hashMap3 = new HashMap<>();
                                                        hashMap3.put("Address", userPaymentOrders11.getAddress());
                                                        hashMap3.put("GrandTotalPrice", userPaymentOrders11.getGrandTotalPrice());
                                                        hashMap3.put("MobileNumber", userPaymentOrders11.getMobileNumber());
                                                        hashMap3.put("Name", userPaymentOrders11.getName());
                                                        hashMap3.put("Note", userPaymentOrders11.getNote());
                                                        hashMap3.put("RandomUID", RandomUID);
                                                        hashMap3.put("Status", "Your order is waiting to be prepared by Chef...");
                                                        FirebaseDatabase.getInstance().getReference("MessWaitingOrders").child(MessID).child(RandomUID).child("OtherInformation").setValue(hashMap3).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                FirebaseDatabase.getInstance().getReference("MessPaymentOrders").child(MessID).child(RandomUID).child("Dishes").removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                        FirebaseDatabase.getInstance().getReference("MessPaymentOrders").child(MessID).child(RandomUID).child("OtherInformation").removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                            @Override
                                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                                FirebaseDatabase.getInstance().getReference("UserPaymentOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUID).child("Dishes").removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                    @Override
                                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                                        FirebaseDatabase.getInstance().getReference("UserPaymentOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUID).child("OtherInformation").removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                            @Override
                                                                                            public void onSuccess(Void aVoid) {
                                                                                                FirebaseDatabase.getInstance().getReference().child("Tokens").child(MessID).child("token").addListenerForSingleValueEvent(new ValueEventListener() {
                                                                                                    @Override
                                                                                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                                                        String usertoken = dataSnapshot.getValue(String.class);
                                                                                                        sendNotifications(usertoken, "Order Confirmed", "Payment mode is confirmed by user, Now you can start the order", "Confirm");
                                                                                                    }

                                                                                                    @Override
                                                                                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                                                                                    }
                                                                                                });

                                                                                            }
                                                                                        }).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                            @Override
                                                                                            public void onSuccess(Void aVoid) {
                                                                                                AlertDialog.Builder builder = new AlertDialog.Builder(UserPayment.this);
                                                                                                builder.setMessage("Payment mode confirmed, Now you can track your order.");
                                                                                                builder.setCancelable(false);
                                                                                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                                                    @Override
                                                                                                    public void onClick(DialogInterface dialog, int which) {

                                                                                                        dialog.dismiss();
                                                                                                        Intent b = new Intent(UserPayment.this, User_FoodPannel_BottomNavigation.class);
                                                                                                        b.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                                                                        startActivity(b);
                                                                                                        finish();

                                                                                                    }
                                                                                                });
                                                                                                AlertDialog alert = builder.create();
                                                                                                alert.show();
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
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

    private void sendNotifications(String usertoken, String title, String message, String confirm) {
//        ContactsContract.Data data = new Data(title, message, confirm);
//        NotificationSender sender = new NotificationSender(data, usertoken);
//        apiService.sendNotification(sender).enqueue(new Callback<MyResponse>() {
//            @Override
//            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
//                if (response.code() == 200) {
//                    if (response.body().success != 1) {
//                        Toast.makeText(CustomerPayment.this, "Failed", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MyResponse> call, Throwable t) {
//
//            }
//        });
    }
}