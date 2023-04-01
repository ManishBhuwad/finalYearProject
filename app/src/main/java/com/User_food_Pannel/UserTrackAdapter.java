package com.User_food_Pannel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.manishproject.R;

import java.util.List;

public class UserTrackAdapter extends RecyclerView.Adapter<UserTrackAdapter.ViewHolder> {

    private Context context;
    private List<User_Final_Order> userFinalOrderslist;

    public UserTrackAdapter(Context context, List<User_Final_Order> userFinalOrderslist) {
        this.userFinalOrderslist = userFinalOrderslist;
        this.context = context;
    }


    @NonNull
    @Override
    public UserTrackAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_trackorder, parent, false);
        return new UserTrackAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserTrackAdapter.ViewHolder holder, int position) {
        final User_Final_Order userFinalOrders = userFinalOrderslist.get(position);
        holder.Dishname.setText(userFinalOrders.getDishName());
        holder.Quantity.setText(userFinalOrders.getDishQuantity() + "× ");
        holder.Totalprice.setText("₹ " + userFinalOrders.getTotalPrice());
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Dishname, Quantity, Totalprice;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Dishname = itemView.findViewById(R.id.dishnm);
            Quantity = itemView.findViewById(R.id.dishqty);
            Totalprice = itemView.findViewById(R.id.totRS);
        }
    }
}
