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

public class UserCartAdapter extends RecyclerView.Adapter<UserCartAdapter.ViewHolder> {

    private Context mcontext;
    private List<Cart> cartModellist;
    static int total = 0;


    public UserCartAdapter(Context context, List<Cart> cartModellist) {
        this.cartModellist = cartModellist;
        this.mcontext = context;
        total = 0;
    }

    @NonNull
    @Override
    public UserCartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.cart_placeorder, parent, false);
        return new UserCartAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserCartAdapter.ViewHolder holder, int position) {
        final Cart cart = cartModellist.get(position);
        holder.dishname.setText(cart.getDishName());
        holder.PriceRs.setText("Price: ₹ " + cart.getPrice());
        holder.Qty.setText("× " + cart.getDishQuantity());
        holder.Totalrs.setText("Total: ₹ " + cart.getTotalprice());
        total += Integer.parseInt(cart.getTotalprice());
//        holder.elegantNumberButton.setNumber(cart.getDishQuantity());
        final int dishprice = Integer.parseInt(cart.getPrice());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView dishname, PriceRs, Qty, Totalrs;
//        ElegantNumberButton elegantNumberButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            dishname = itemView.findViewById(R.id.Dishname);
            PriceRs = itemView.findViewById(R.id.pricers);
            Qty = itemView.findViewById(R.id.qty);
            Totalrs = itemView.findViewById(R.id.totalrs);
//            elegantNumberButton = itemView.findViewById(R.id.elegantbtn);
        }
    }
}
