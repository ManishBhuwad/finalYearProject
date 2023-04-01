package com.example.manishproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Choose_One extends AppCompatActivity {
    Button btnMess, btnUser, btnDeliveryPerson;
    Intent intent;
    String type;
    ConstraintLayout bgimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_one);

        btnMess = findViewById(R.id.btnMess);
        btnDeliveryPerson = (Button) findViewById(R.id.delivery);
        btnUser = (Button) findViewById(R.id.User);
//        AnimationDrawable animationDrawable = new AnimationDrawable();
//        animationDrawable.addFrame(getResources().getDrawable(R.drawable.bghome2), 3000);
//        animationDrawable.addFrame(getResources().getDrawable(R.drawable.pic2), 3000);
//        animationDrawable.addFrame(getResources().getDrawable(R.drawable.pic3), 3000);
//        animationDrawable.addFrame(getResources().getDrawable(R.drawable.pic5), 3000);
//        animationDrawable.addFrame(getResources().getDrawable(R.drawable.pic6), 3000);
//        animationDrawable.addFrame(getResources().getDrawable(R.drawable.bggg), 3000);
//        animationDrawable.addFrame(getResources().getDrawable(R.drawable.pic9), 3000);
//        animationDrawable.addFrame(getResources().getDrawable(R.drawable.pic10), 3000);
//        animationDrawable.addFrame(getResources().getDrawable(R.drawable.pic11), 3000);
//        animationDrawable.addFrame(getResources().getDrawable(R.drawable.pic12), 3000);
//        animationDrawable.addFrame(getResources().getDrawable(R.drawable.pic13), 3000);
//        animationDrawable.addFrame(getResources().getDrawable(R.drawable.pic14), 3000);



//        animationDrawable.setOneShot(false);
//        animationDrawable.setEnterFadeDuration(850);
//        animationDrawable.setExitFadeDuration(1600);
//        bgimage = findViewById(R.id.back3)
//        bgimage.setBackgroundDrawable(animationDrawable);
//        animationDrawable.start();
        intent = getIntent();
        type = intent.getStringExtra("Home").toString().trim();

        btnMess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type.equals("Email")) {
                    Intent loginemail = new Intent(Choose_One.this, Mess_login.class);
                    startActivity(loginemail);
                    finish();
                }
                if (type.equals("Phone")) {
                    Intent loginphone = new Intent(Choose_One.this, Mess_Phone_login.class);
                    startActivity(loginphone);
                    finish();
                }
                if (type.equals("SignUp")) {
                    Intent Register = new Intent(Choose_One.this, Mess_Registration.class);
                    startActivity(Register);


                }

            }
        });

        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type.equals("Email")) {
                    Intent loginemailcust = new Intent(Choose_One.this, Login.class);
                    startActivity(loginemailcust);
                    finish();
                }
                if (type.equals("Phone")) {
                    Intent loginphonecust = new Intent(Choose_One.this, Login_Phone.class);
                    startActivity(loginphonecust);
                    finish();
                }
                if (type.equals("SignUp")) {
                    Intent Registercust = new Intent(Choose_One.this, Registration.class);
                    startActivity(Registercust);
                }
            }
        });

        btnDeliveryPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type.equals("SignUp")) {
                    Intent Registerdelivery = new Intent(Choose_One.this, Delivery_Registration.class);
                    startActivity(Registerdelivery);
                    finish();
                }
                if (type.equals("Phone")) {
                    Intent loginphone = new Intent(Choose_One.this, Delivery_Phone_login.class);
                    startActivity(loginphone);
                    finish();
                }
                if (type.equals("Email")) {
                    Intent loginemail = new Intent(Choose_One.this, Delivery_Login.class);
                    startActivity(loginemail);

                }
            }
        });
    }
}