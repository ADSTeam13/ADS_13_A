package com.example.yak.si_kk2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUser extends AppCompatActivity {
    EditText mUsername, mEmail, mPassword, mPhone, mNamaKomunitas;
    Spinner mJenisPengguna;
    Button mDaftar;
   // Spinner namaKomunitas;
   // RadioButton daftarKomunitas, daftarUser;
    FirebaseDatabase database;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    ProgressDialog mProgress;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mUsername = (EditText) findViewById(R.id.daftarUsername);
        mEmail = (EditText) findViewById(R.id.daftarEmailUser);
        mPassword = (EditText) findViewById(R.id.datftarPassword);
        mPhone = (EditText) findViewById(R.id.daftarPhone);
        mNamaKomunitas = (EditText) findViewById(R.id.namaKomunitas);
        mJenisPengguna = (Spinner) findViewById(R.id.jenisPengguna);
        mDaftar = (Button) findViewById(R.id.daftarUser);
        mDatabase = FirebaseDatabase.getInstance().getReference("users");
        mProgress = new ProgressDialog(this);
        mDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartRegister();
            }
        });
    }

    private void StartRegister() {
        final String namauser = mUsername.getText().toString().trim();
        final String email = mEmail.getText().toString().trim();
        final String password = mPassword.getText().toString().trim();
        final String phone = mPhone.getText().toString().trim();
        final String namakomunitas = mNamaKomunitas.getText().toString().trim();
        final String jenispengguna = mJenisPengguna.getSelectedItem().toString();
        mProgress.setMessage("Signing Up...");
        mProgress.show();
        if(!TextUtils.isEmpty(namauser)||!TextUtils.isEmpty(email)||
                !TextUtils.isEmpty(password)|| !TextUtils.isEmpty(phone)||
                !TextUtils.isEmpty(namakomunitas)|| !TextUtils.isEmpty(jenispengguna)){
                        String user_id = mDatabase.push().getKey();
                        User user = new User(namauser,email, password, phone,namakomunitas,jenispengguna);
                        mDatabase.child(user_id).setValue(user);
                        mProgress.dismiss();
            Toast.makeText(this, "Data Dimasukan", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Masukan data dengan benar...", Toast.LENGTH_LONG).show();
        }

    }

}
