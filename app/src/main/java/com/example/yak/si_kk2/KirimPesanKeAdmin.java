package com.example.yak.si_kk2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class KirimPesanKeAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kirim_pesan_ke_admin);
    }


    public void onKirimKritikAdmin(View view) {
        Intent intent = new Intent(this,MenuUtamaUser.class);
        startActivity(intent);
        Toast.makeText(this,"Berhasil Mengirim Pesan Ke Admin",Toast.LENGTH_LONG).show();
    }
}
