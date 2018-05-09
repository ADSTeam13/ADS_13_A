package com.example.yak.si_kk2;

import android.*;
import android.Manifest;
import android.app.ProgressDialog;

import java.io.ByteArrayOutputStream;
import java.lang.String;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth = null;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();
    EditText mUserName, mEmail, mPassword, mPhone;
    Spinner mModePengguna;
    Button mDaftar;
    ImageView imgProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        init();
        imgProfile = (ImageView) findViewById(R.id.imgProfile);
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckPermission();
            }
        });
    }

    private void init() {
        mEmail = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);
        mUserName = (EditText) findViewById(R.id.usernameId);
        mPhone = (EditText) findViewById(R.id.phone);
        mModePengguna = (Spinner) findViewById(R.id.pilihMode);
        mPassword = (EditText) findViewById(R.id.password);
        mDaftar = (Button) findViewById(R.id.email_sign_up_button);
    }
    private void SaveImageToFirebase() {
        final FirebaseUser currentUser = mAuth.getCurrentUser();
        String email = currentUser.getEmail().toString();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl("gs://si-kk-team13.appspot.com");
        SimpleDateFormat df = new SimpleDateFormat("ddMMyyHHmmss");
        Date dataObject = new Date();
        String imgPath = SplitString(email)+"."+df.format(dataObject) +".jpg";
        StorageReference imageRef = storageRef.child("images/"+imgPath);
        imgProfile.isDrawingCacheEnabled();
        imgProfile.buildDrawingCache();

        Drawable drawable =imgProfile.getDrawable();
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
        byte[] data = baos.toByteArray();
        UploadTask uploadTask = imageRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"fail to Upload data", Toast.LENGTH_LONG).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                String DownloadURL = taskSnapshot.getDownloadUrl().toString();
                myRef.child("Users").child(currentUser.getUid()).child("email").setValue(currentUser.getEmail());
                myRef.child("Users").child(currentUser.getUid()).child("profileimage").setValue(DownloadURL);
            }
        });
    }
    private  void LoadLogin(){
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra("email",currentUser.getEmail());
            intent.putExtra("uid",currentUser.getUid());
            startActivity(intent);
        }
    }

    private  String SplitString(String email){
        String[] split = email.split("@");
        return split[0];
    }

    int READIMAGE = 253;
    void CheckPermission(){
        if(Build.VERSION.SDK_INT>=23){
            if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                String[] PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(PERMISSIONS,READIMAGE);
                return;
            }
        }
        UploadImage();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == READIMAGE) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                UploadImage();
            }else{
                Toast.makeText(this, "Cannot Acces Your Images", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }
    int PICK_IMAGE_CODE =123;
    public void UploadImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,PICK_IMAGE_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE_CODE && data != null){
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn,null,null,null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            imgProfile.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }
    }
    public void BtnRegister(View v){
        String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        }
        //progressBar.setVisibility(View.VISIBLE);
        //create user
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(RegisterActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        //   progressBar.setVisibility(View.GONE);
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (task.isSuccessful()) {
                            saveData();
                            //startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            Toast.makeText(RegisterActivity.this, "Authentication Succes and Click Login Button To Login." + task.getException(),
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(RegisterActivity.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void saveData() {
        String _username = mUserName.getText().toString().trim();
        String _phone = mPhone.getText().toString().trim();
        String _modepengguna = (String) mModePengguna.getSelectedItem();
        String _password = mPassword.getText().toString().trim();
        final FirebaseUser currentUser = mAuth.getCurrentUser();
        myRef.child("Users").child(currentUser.getUid()).child("username").setValue(_username);
        myRef.child("Users").child(currentUser.getUid()).child("phone").setValue(_phone);
        myRef.child("Users").child(currentUser.getUid()).child("modepengguna").setValue(_modepengguna);
        myRef.child("Users").child(currentUser.getUid()).child("password").setValue(_password);
        SaveImageToFirebase();
      //  LoadLogin();
    }
    @Override
    protected void onStart() {
        mAuth.getInstance().signOut();
        super.onStart();
    }
    protected void onResume() {
        super.onResume();
        //progressBar.setVisibility(View.GONE);
    }
    public void onLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
