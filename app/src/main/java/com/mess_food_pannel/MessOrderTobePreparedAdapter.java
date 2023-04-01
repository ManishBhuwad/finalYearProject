package com.mess_food_pannel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.manishproject.MessOrderTobePrepared;
import com.example.manishproject.R;

import java.util.List;

public class MessOrderTobePreparedAdapter extends RecyclerView.Adapter<MessOrderTobePreparedAdapter.ViewHolder> {

    private Context context;
    private List<MessWaitingOrders1> messWaitingOrders1list;



    public MessOrderTobePreparedAdapter(MessOrderTobePrepared messOrderTobePrepared, List<MessWaitingOrders1> messWaitingOrders1List) {
    }

    @NonNull
    @Override
    public MessOrderTobePreparedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.mess_ordertobeprepared, parent, false);
        return new MessOrderTobePreparedAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessOrderTobePreparedAdapter.ViewHolder holder, int position) {
        MessWaitingOrders1 messWaitingOrders1 = messWaitingOrders1list.get(position);
        holder.Address.setText(messWaitingOrders1.getAddress());
        holder.grandtotalprice.setText("Grand Total: ₹ " + messWaitingOrders1.getGrandTotalPrice());
        final String random = messWaitingOrders1.getRandomUID();
        holder.Vieworder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MessOrdertobePrepareView.class);
                intent.putExtra("RandomUID", random);
                context.startActivity(intent);
                ((MessOrderTobePrepared) context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return messWaitingOrders1list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView Address, grandtotalprice;
        Button Vieworder;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Address = itemView.findViewById(R.id.cust_address);
            grandtotalprice = itemView.findViewById(R.id.Grandtotalprice);
            Vieworder = itemView.findViewById(R.id.View_order);
        }
    }
}
