package com.example.yak.si_kk2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuUtamaKomunitas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama_komunitas);
    }
    public void onProfileKomunitas(View view){
        Intent intent = new Intent(this, ProfileKomunitas.class);
        startActivity(intent);
    }
    public void onLogOutKomunitas(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    public void onPortofolioKomunitas(View view){
        Intent intent = new Intent(this, PortofolioKomunitas.class);
        startActivity(intent);
    }
    public void onSewaKomunitas(View view){
        Intent intent = new Intent(this, ListOrder.class);
        startActivity(intent);
    }
}
