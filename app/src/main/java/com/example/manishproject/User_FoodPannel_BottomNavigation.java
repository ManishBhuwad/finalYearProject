package com.example.manishproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.User_food_Pannel.User_Cart_Fragment;
import com.User_food_Pannel.User_Home_Fragment;
import com.User_food_Pannel.User_Order_Fragment;
import com.User_food_Pannel.User_Profile_Fragment;
import com.User_food_Pannel.User_Track_Fragment;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.mess_food_pannel.Mess_Home_Fragment;
import com.mess_food_pannel.Mess_Order_Fragment;
import com.mess_food_pannel.Mess_PendingOrders_Fragment;
import com.mess_food_pannel.Mess_Profile_Fragment;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class User_FoodPannel_BottomNavigation extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_food_pannel_bottom_navigation);
        MeowBottomNavigation navigationView = findViewById(R.id.bottom_navigation);


        navigationView.add(new MeowBottomNavigation.Model(1, R.drawable.baseline_home_24));
        navigationView.add(new MeowBottomNavigation.Model(2, R.drawable.baseline_shopping_cart_24));
        navigationView.add(new MeowBottomNavigation.Model(3, R.drawable.baseline_order_24));
        navigationView.add(new MeowBottomNavigation.Model(4, R.drawable.baseline_track_24));
        navigationView.add(new MeowBottomNavigation.Model(5, R.drawable.baseline_person_24));


        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.messGreenColor)));

        navigationView.show(1, true);


        navigationView.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES
                Fragment fragment;
                if(model.getId()==5){
                   fragment = new User_Profile_Fragment();
                }
                else if (model.getId() == 4) {
                    fragment = new User_Track_Fragment();
                } else if (model.getId() == 3) {
                    fragment = new User_Order_Fragment();
                } else if (model.getId() == 2) {
                    fragment = new User_Cart_Fragment();
                } else {
                    fragment = new User_Home_Fragment();
                }

                loadFragment(fragment);

                return null;
            }
        });

        navigationView.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
//                Toast.makeText(Mess_Bottom_Navigation.this, "You are reselected", Toast.LENGTH_SHORT).show();
                return null;
            }
        });

//        navigationView.setCount(3, "10");


        UpdateToken();

    }


    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,fragment,null)
                .commit();
    }

        private void UpdateToken() {
//        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
                @Override
                public void onComplete(@NonNull Task<String> task) {
                    if(task.isComplete()){
                        String token = task.getResult();
                        FirebaseDatabase.getInstance().getReference("Tokens").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(token);



                    }
                }
            });
//        String refreshToken = FirebaseInstanceId.getInstance().getToken();
//        Token token = new Token(refreshToken);
//        FirebaseDatabase.getInstance().getReference("Tokens").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(token);
        }
}