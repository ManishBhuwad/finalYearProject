package com.mess_food_pannel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.manishproject.R;

import java.util.List;

public class MessOrderDishesAdapter extends RecyclerView.Adapter<MessOrderDishesAdapter.ViewHolder>{

    private Context mcontext;
    private List<MessPendingOrders> messPendingOrdersList;



    public MessOrderDishesAdapter(com.example.manishproject.Mess_Order_Dishes mess_order_dishes, List<MessPendingOrders> messPendingOrdersList) {
    }

    @NonNull
    @Override
    public MessOrderDishesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.activity_mess_order_dishes, parent, false);
        return new MessOrderDishesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessOrderDishesAdapter.ViewHolder holder, int position) {
        final MessPendingOrders chefPendingOrders = messPendingOrdersList.get(position);
        holder.dishname.setText(chefPendingOrders.getDishName());
        holder.price.setText("Price: ₹ " + chefPendingOrders.getPrice());
        holder.quantity.setText("× " + chefPendingOrders.getDishQuantity());
        holder.totalprice.setText("Total: ₹ " + chefPendingOrders.getTotalPrice());
    }



    @Override
    public int getItemCount() {
        return messPendingOrdersList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView dishname, price, totalprice, quantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            dishname = itemView.findViewById(R.id.DN);
            price = itemView.findViewById(R.id.PR);
            totalprice = itemView.findViewById(R.id.TR);
            quantity = itemView.findViewById(R.id.QY);
        }
    }
}
