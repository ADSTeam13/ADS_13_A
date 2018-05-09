package com.example.yak.si_kk2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_order);

        ArrayList<Order> orders = new ArrayList<Order>();
        for(int i=0;i<100;i++){
            orders.add(new Order("Sugianto","Memesan : Kesenenian Jaran Kepang, pelaksanaan: 20/06/2018, Jam: 10 pagi"));
            orders.add(new Order("Budiono","Memesan : Tari Topeng Malangan, pelaksanaan: 20/06/2018, Jam: 10 pagi"));
            orders.add(new Order("Aliando","Memesan : Bantengan, pelaksanaan: 20/06/2018, Jam: 10 pagi"));
            orders.add(new Order("Firma Sutisna","Memesan : Reog Ponorogo Jatim, pelaksanaan: 20/06/2018, Jam: 10 pagi"));
            orders.add(new Order("Feri Setiawan","Memesan : Betawi, pelaksanaan: 20/06/2018, Jam: 10 pagi"));
            orders.add(new Order("Paijo","Memesan : Jaran Goyang, pelaksanaan: 20/06/2018, Jam: 10 pagi"));
            orders.add(new Order("Asri Wulandari","Memesan : Jaran Jaranan, pelaksanaan: 20/06/2018, Jam: 10 pagi"));
            orders.add(new Order("AKbar Maulana","Memesan : Jaran VIa Vallen, pelaksanaan: 20/06/2018, Jam: 10 pagi"));
            orders.add(new Order("Khoirudin","Memesan : Reog Vs Jaranan, pelaksanaan: 20/06/2018, Jam: 10 pagi"));
            orders.add(new Order("Karim Abdul","Memesan : Topeng Lamongan, pelaksanaan: 20/06/2018, Jam: 10 pagi"));
        }
        OrderAdapter orderAdapter = new OrderAdapter(this, orders);
        ListView listView = (ListView) findViewById(R.id.listOrder);
        listView.setAdapter(orderAdapter);
    } catch (Exception e) {
        Log.d("ListOrder", e.toString());
    }
}
    public void onOrder(View v){
        Intent intent = new Intent(this, AcceptOrder.class);
        startActivity(intent);

    }
}
