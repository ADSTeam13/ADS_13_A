package com.example.yak.si_kk2;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Yak on 07/05/2018.
 */

public class PostFeedKomunitasAdapter extends ArrayAdapter<PostFeedKomunitas> {
    public PostFeedKomunitasAdapter(Activity context, ArrayList<PostFeedKomunitas> postFeedKomunitas) {
        super(context, 0, postFeedKomunitas);
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.feed, parent, false);
        }

        PostFeedKomunitas pfk;
        pfk = getItem(position);
        ImageView ivProfile = (ImageView) listItemView.findViewById(R.id.ivProfile);
        ivProfile.setImageResource(pfk.getIvResID());
        TextView tvJudul = (TextView) listItemView.findViewById(R.id.tvJudul);
        tvJudul.setText(pfk.getPostJudul());
        TextView tvDescription = (TextView) listItemView.findViewById(R.id.tvDescription);

        tvDescription.setText(pfk.getPostDescription());

        TextView tvBiaya = (TextView) listItemView.findViewById(R.id.tvBiaya);
        tvBiaya.setText("Rp. "+pfk.getBiaya());
        RatingBar tvRate = (RatingBar) listItemView.findViewById(R.id.rbRate);
        tvRate.setRating(pfk.getRate());
        return listItemView;
    }
}
