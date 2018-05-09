package com.example.yak.si_kk2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class AcceptOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_order);
    }
    public void onTerima(View v){
        Intent intent = new Intent(this, ListOrder.class);
        startActivity(intent);
        Toast.makeText(this,"Order Diterima, Pengiriman Pemberitahuan Ke User Pemesan segera dilakukan",Toast.LENGTH_LONG).show();
    }
    public void onTidakDiterima(View v){
        Intent intent = new Intent(this, ListOrder.class);
        startActivity(intent);
        Toast.makeText(this,"Order Tidak Diterima, Pengiriman Pemberitahuan Ke User Pemesan segera dilakukan",Toast.LENGTH_LONG).show();
    }
}
