package com.example.yak.si_kk2;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Yak on 02/05/2018.
 */

public class User {
    public String username;
    public String email;
    public String password;
    public String phone;
    public String jenisPengguna;
    public String namaKomunitas;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }


    public User(String username, String email, String password,String phone, String jenisPengguna, String namaKomunitas) {
        this.username = username;
        this.email = email;
        this.password =password;
        this.phone = phone;
        this.jenisPengguna = jenisPengguna;
        this.namaKomunitas = namaKomunitas;
    }
    private void Init(){

    }
}
