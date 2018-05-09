package com.example.yak.si_kk2;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;

import static com.google.firebase.internal.FirebaseAppHelper.getUid;

public class MenuUtamaUser extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseRef;
    private ImageView ivProfile;
    private TextView usernameId, emailId;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAuth.getInstance();
            init();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

            ArrayList<PostFeedKomunitas> postFeedKomunitas = new ArrayList<PostFeedKomunitas>();
            for(int i=0;i<100;i++){
                postFeedKomunitas.add(new PostFeedKomunitas(1, R.drawable.ic_menu_camera, "Jaran Kepang Malangan", "description", 1234, 5));
                postFeedKomunitas.add(new PostFeedKomunitas(1, R.drawable.ic_menu_gallery, "judul", "description", 1234, 1));
                postFeedKomunitas.add(new PostFeedKomunitas(1, R.drawable.ic_menu_manage, "judul", "description", 1234, 3));
                postFeedKomunitas.add(new PostFeedKomunitas(1, R.drawable.ic_menu_send, "judul", "description", 1234, 2));
                postFeedKomunitas.add(new PostFeedKomunitas(1, R.drawable.ic_menu_share, "judul", "description", 1234, 4));
                postFeedKomunitas.add(new PostFeedKomunitas(1, R.drawable.ic_menu_slideshow, "judul", "description", 1234, 4));
                postFeedKomunitas.add(new PostFeedKomunitas(1, R.drawable.common_google_signin_btn_icon_dark, "judul", "description", 1234, 5));
                postFeedKomunitas.add(new PostFeedKomunitas(1, R.drawable.common_google_signin_btn_icon_dark_normal, "judul", "description", 1234, 5));
                postFeedKomunitas.add(new PostFeedKomunitas(1, R.drawable.common_full_open_on_phone, "judul", "description", 1234, 4));
                postFeedKomunitas.add(new PostFeedKomunitas(1, R.drawable.person_outline, "judul", "description", 1234, 3));
            }
            PostFeedKomunitasAdapter postFeed = new PostFeedKomunitasAdapter(this, postFeedKomunitas);
            ListView listView = (ListView) findViewById(R.id.lvF);
            listView.setAdapter(postFeed);
        } catch (Exception e) {
            Log.d("MenuUtamaUser", e.toString());
        }
//        final ArrayList<String> itemlist = new ArrayList<String>();
//
//        final FirebaseUser currentUser = mAuth.getCurrentUser();
////Single event listener
//        mDatabaseRef.child("Users").addListenerForSingleValueEvent(
//                new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        // Get user value
//                        for(DataSnapshot items: dataSnapshot.getChildren()){
//                            user = items.getValue(User.class);
//                            //itemlist.add(user.email);
//                            //itemlist.add(user.username);
//                            emailId.setText(user.email);
//                            usernameId.setText(user.username);
//                        }
//                    }
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });
    }
    void init(){
        ivProfile = (ImageView) findViewById(R.id.ivProfileId);
        emailId = (TextView) findViewById(R.id.tvEmailId);
        usernameId = (TextView) findViewById(R.id.tvUsernameId);
    }
    public void onProfileKomunitas(View view){
        Intent intent = new Intent(this, ProfileKomunitas.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_utama_user, menu);
        return true;
    }
    void GetProfile(){


        try {
            final File tmpFile = File.createTempFile("img", "png");
            StorageReference reference = FirebaseStorage.getInstance().getReference("Photos");
            reference.child("images/").getFile(tmpFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap image = BitmapFactory.decodeFile(tmpFile.getAbsolutePath());
                    ivProfile.setImageBitmap(image);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, MenuUtamaKomunitas.class);
            startActivity(intent);
        }
        if(id==R.id.action_logout){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            mAuth.getInstance().signOut();
        }else{
            Toast.makeText(this,"Cannot Logout",Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_camera) {
            Intent intent = new Intent(this,RiwayatPemesanan.class);
            startActivity(intent);
            Toast.makeText(this,"Masih Dalam Tahap Pengembangan",Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(this,NotifikasiForUser.class);
            startActivity(intent);
            Toast.makeText(this,"Masih Dalam Tahap Pengembangan",Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(this,WhistList.class);
            startActivity(intent);
            Toast.makeText(this,"Masih Dalam Tahap Pengembangan",Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(this,PengaturanAkun.class);
            startActivity(intent);
            Toast.makeText(this,"Masih Dalam Tahap Pengembangan",Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_share) {
            Toast.makeText(this,"Masih Dalam Tahap Pengembangan",Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_send) {
            Intent intent = new Intent(this,KirimPesanKeAdmin.class);
            startActivity(intent);
            Toast.makeText(this,"Masih Dalam Tahap Pengembangan",Toast.LENGTH_LONG).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private  void SignOut(){
        FirebaseUser currentUser = mAuth.getCurrentUser();
        mAuth.getInstance().signOut();
        if(currentUser==null){
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra("email",currentUser.getEmail());
            intent.putExtra("uid",currentUser.getUid());
            startActivity(intent);
        }else{
            Toast.makeText(this,"Cannot Logout",Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
       // GetProfile();
    }
}
