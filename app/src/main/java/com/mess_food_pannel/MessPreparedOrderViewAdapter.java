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

public class MessPreparedOrderViewAdapter extends RecyclerView.Adapter<MessPreparedOrderViewAdapter.ViewHolder> {


    private Context mcontext;
    private List<MessFinalOrders> messFinalOrderslist;

    public MessPreparedOrderViewAdapter(Context context, List<MessFinalOrders> messFinalOrderslist) {
        this.messFinalOrderslist = messFinalOrderslist;
        this.mcontext = context;
    }

    @NonNull
    @Override
    public MessPreparedOrderViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.mess_prepared_order_view, parent, false);
        return new MessPreparedOrderViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessPreparedOrderViewAdapter.ViewHolder holder, int position) {
        final MessFinalOrders messFinalOrders=messFinalOrderslist.get(position);
        holder.dishname.setText(messFinalOrders.getDishName());
        holder.price.setText("Price: ₹ " + messFinalOrders.getDishPrice());
        holder.quantity.setText("× " + messFinalOrders.getDishQuantity());
        holder.totalprice.setText("Total: ₹ " + messFinalOrders.getTotalPrice());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView dishname, price, totalprice, quantity;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            dishname = itemView.findViewById(R.id.Cdishname);
            price = itemView.findViewById(R.id.Cdishprice);
            totalprice = itemView.findViewById(R.id.Ctotalprice);
            quantity = itemView.findViewById(R.id.Cdishqty);
        }
    }
}
