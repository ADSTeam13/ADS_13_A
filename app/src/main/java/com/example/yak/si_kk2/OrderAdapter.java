package com.example.yak.si_kk2;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Yak on 08/05/2018.
 */
public class OrderAdapter extends ArrayAdapter<Order> {
    public OrderAdapter(Activity context, ArrayList<Order> orders) {
        super(context, 0, orders);
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.order, parent, false);
        }

        Order order;
        order = getItem(position);

        TextView tvNameOrder = (TextView) listItemView.findViewById(R.id.tvNamaPemesan);
        tvNameOrder.setText(order.getOrderName());
        TextView tvDetilOrder = (TextView) listItemView.findViewById(R.id.tvDetilPesanan);
        tvDetilOrder.setText(order.getDetilOrder());
        return listItemView;
    }
}

