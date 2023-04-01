package com.User_food_Pannel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.manishproject.R;
import com.google.firebase.database.DatabaseReference;
import com.mess_food_pannel.UpdateDishModel;

import java.util.List;

public class UserHomeAdapter extends RecyclerView.Adapter<UserHomeAdapter.ViewHolder>{

    private Context mcontext;
    private List<UpdateDishModel>updateDishModellist;
    DatabaseReference databaseReference;
    public UserHomeAdapter(Context context, List<UpdateDishModel> updateDishModellist) {
        this.updateDishModellist=updateDishModellist;
        this.mcontext=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mcontext).inflate(R.layout.user_menudish,parent,false);
        return new UserHomeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHomeAdapter.ViewHolder holder, int position) {
        final UpdateDishModel updateDishModel=updateDishModellist.get(position);
//        Glide.with(mcontext).load(updateDishModel.getImageURL()).into(holder.imageView);
        holder.Dishname.setText(updateDishModel.getDishes());
        updateDishModel.getRandomUID();
        updateDishModel.getMessId();
        holder.price.setText("Price: ₹ " + updateDishModel.getPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(mcontext,OrderDish.class);
                intent.putExtra("FoodMenu",updateDishModel.getRandomUID());
                intent.putExtra("MessId",updateDishModel.getMessId());


                mcontext.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView Dishname,price;
//        ElegantNumberButton additem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.menu_image);
            Dishname=itemView.findViewById(R.id.dishname);
            price=itemView.findViewById(R.id.dishprice);
//            additem=itemView.findViewById(R.id.number_btn);
        }
    }
}
