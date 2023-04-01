package com.example.manishproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.mess_food_pannel.Mess_Home_Fragment;
import com.mess_food_pannel.Mess_Order_Fragment;
import com.mess_food_pannel.Mess_PendingOrders_Fragment;
import com.mess_food_pannel.Mess_Profile_Fragment;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class Mess_Bottom_Navigation extends AppCompatActivity  {



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_bottom_navigation);
        MeowBottomNavigation navigationView = findViewById(R.id.bottom_navigation1);
        navigationView.add(new MeowBottomNavigation.Model(1, R.drawable.baseline_home_24));
        navigationView.add(new MeowBottomNavigation.Model(2, R.drawable.baseline_order_24));
        navigationView.add(new MeowBottomNavigation.Model(3, R.drawable.baseline_content_paste_24));
        navigationView.add(new MeowBottomNavigation.Model(4, R.drawable.baseline_restaurant_24));

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.messGreenColor)));

        navigationView.show(1, true);

//        Toast.makeText(Mess_Bottom_Navigation.this, "aaaa", Toast.LENGTH_SHORT).show();


        navigationView.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES
                Fragment fragment;
                if(model.getId()==4){
                    fragment = new Mess_Profile_Fragment();
                }
                else if (model.getId() == 3) {
                    fragment = new Mess_Order_Fragment();
                } else if (model.getId() == 2) {
                    fragment = new Mess_PendingOrders_Fragment();
                } else {
                    fragment = new Mess_Home_Fragment();
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
//        String name = getIntent().getStringExtra("PAGE");
//        if (name != null) {
//            if (name.equalsIgnoreCase("Orderpage")) {
//                loadmessfragment(new Mess_PendingOrders_Fragment());
//            } else if (name.equalsIgnoreCase("Confirmpage")) {
//                loadmessfragment(new Mess_Order_Fragment());
//            } else if (name.equalsIgnoreCase("AcceptOrderpage")) {
//                loadmessfragment(new Mess_Home_Fragment());
//            } else if (name.equalsIgnoreCase("Deliveredpage")) {
//                loadmessfragment(new Mess_Home_Fragment());
//            }
//        } else {
//            loadmessfragment(new Mess_Home_Fragment());
//        }
//    }



//    private boolean loadmessfragment(Fragment fragment) {
//        if (fragment != null) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
//            return true;
//        }
//
//        return false;
//    }

//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//        Fragment fragment = null;
//        switch (menuItem.getItemId()) {
//            case R.id.Home:
//                fragment = new Mess_Home_Fragment();
//                break;
//
//            case R.id.PendingOrders:
//                fragment = new Mess_PendingOrders_Fragment();
//                break;
//
//            case R.id.Orders:
//                fragment = new Mess_Order_Fragment();
//                break;
//            case R.id.chefProfile:
//                fragment = new Mess_Profile_Fragment();
//                break;
//        }
//        return loadmessfragment(fragment);
//    }
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,fragment,null)
                .commit();
    }

    private void UpdateToken() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (task.isComplete()) {
                    String token = task.getResult();
                    FirebaseDatabase.getInstance().getReference("Tokens").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(token);


                }
            }
        });
    }

}