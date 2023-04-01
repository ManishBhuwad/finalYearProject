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

public class PendingOrdersAdapter extends RecyclerView.Adapter<PendingOrdersAdapter.ViewHolder> {

    private Context context;
    private List<UserPendingOrders> userPendingOrderslist;

    public PendingOrdersAdapter(Context context, List<UserPendingOrders> userPendingOrderslist) {
        this.userPendingOrderslist = userPendingOrderslist;
        this.context = context;
    }

    @NonNull
    @Override
    public PendingOrdersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pending_order_dishes, parent, false);
        return new PendingOrdersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PendingOrdersAdapter.ViewHolder holder, int position) {
        final UserPendingOrders userPendingOrders = userPendingOrderslist.get(position);
        holder.Dishname.setText(userPendingOrders.getDishName());
        holder.Price.setText("Price: ₹ " + userPendingOrders.getPrice());
        holder.Quantity.setText("× " + userPendingOrders.getDishQuantity());
        holder.Totalprice.setText("Total: ₹ " + userPendingOrders.getTotalPrice());
    }

    @Override
    public int getItemCount() {
        return userPendingOrderslist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView Dishname, Price, Quantity, Totalprice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Dishname = itemView.findViewById(R.id.Dishh);
            Price = itemView.findViewById(R.id.pricee);
            Quantity = itemView.findViewById(R.id.qtyy);
            Totalprice = itemView.findViewById(R.id.total);

        }
    }
}
