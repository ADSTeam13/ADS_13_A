package com.example.yak.si_kk2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RegisterMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_menu);
    }
    public void onRegisterKomunitas(View view){
        Intent intent = new Intent(this, RegisterKomunitas.class);
        startActivity(intent);
    }
    public void onRegisterUser(View view){
        Intent intent = new Intent(this, RegisterUser.class);
        startActivity(intent);
    }
}
