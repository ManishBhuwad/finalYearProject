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

public class PayableOrderAdapter extends RecyclerView.Adapter<PayableOrderAdapter.ViewHolder> {

    private Context context;
    private List<UserPaymentOrders> userPaymentOrderslist;
    public PayableOrderAdapter(Context context, List<UserPaymentOrders> userPendingOrderslist) {
        this.userPaymentOrderslist = userPendingOrderslist;
        this.context = context;

    }

    @NonNull
    @Override
    public PayableOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_payableorder, parent, false);
        return new PayableOrderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PayableOrderAdapter.ViewHolder holder, int position) {

        final UserPaymentOrders userPaymentOrders = userPaymentOrderslist.get(position);
        holder.Dishname.setText(userPaymentOrders.getDishName());
        holder.Price.setText("Price: ₹ " + userPaymentOrders.getDishPrice());
        holder.Quantity.setText("× " + userPaymentOrders.getDishQuantity());
        holder.Totalprice.setText("Total: ₹ " + userPaymentOrders.getTotalPrice());

    }

    @Override
    public int getItemCount() {
        return userPaymentOrderslist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView Dishname, Price, Quantity, Totalprice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Dishname = itemView.findViewById(R.id.dish);
            Price = itemView.findViewById(R.id.pri);
            Quantity = itemView.findViewById(R.id.qt);
            Totalprice = itemView.findViewById(R.id.Tot);
        }
    }
}
