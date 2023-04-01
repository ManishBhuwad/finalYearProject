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

import com.example.manishproject.MessPreparedOrder;
import com.example.manishproject.MessPreparedOrderView;
import com.example.manishproject.R;

import java.util.List;

public class MessPreparedOrderAdapter extends RecyclerView.Adapter<MessPreparedOrderAdapter.ViewHolder> {

    private Context context;
    private List<MessFinalOrders1> messFinalOrders1list;


    public MessPreparedOrderAdapter(com.example.manishproject.MessPreparedOrder messPreparedOrder, List<MessFinalOrders1> messFinalOrders1List) {
    }


    @NonNull
    @Override
    public MessPreparedOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.mess_preparedorder, parent, false);
        return new MessPreparedOrderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessPreparedOrderAdapter.ViewHolder holder, int position) {

        final MessFinalOrders1 messFinalOrders1 = messFinalOrders1list.get(position);
        holder.Address.setText(messFinalOrders1.getAddress());
        holder.grandtotalprice.setText("Grand Total: ₹ " + messFinalOrders1.getGrandTotalPrice());
        final String random = messFinalOrders1.getRandomUID();
        holder.Vieworder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MessPreparedOrderView.class);
                intent.putExtra("RandomUID", random);
                context.startActivity(intent);
                ((MessPreparedOrder) context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Address, grandtotalprice;
        Button Vieworder;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Address = itemView.findViewById(R.id.customer_address);
            grandtotalprice = itemView.findViewById(R.id.customer_totalprice);
            Vieworder = itemView.findViewById(R.id.View);
        }
    }
}
