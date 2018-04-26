package com.example.yak.si_kk2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RegisterKomunitas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_komunitas);
    }
    public void onSuccesRegisterKom(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
