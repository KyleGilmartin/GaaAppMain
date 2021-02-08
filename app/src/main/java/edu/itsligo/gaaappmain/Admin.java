package edu.itsligo.gaaappmain;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.storage.StorageManager;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import edu.itsligo.gaaappmain.Lotto.AdminFragmentLotto;
import edu.itsligo.gaaappmain.Lotto.HelperClass;


public class Admin extends AppCompatActivity {
    private DrawerLayout draw;
    // firebase is used
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        /*
        email = (TextView) findViewById(R.id.emailPlacehold);
        username = (TextView) findViewById(R.id.usernamePlacehold);*/

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);


        draw = findViewById(R.id.drawer_layout);
        // framnet for profile
        //  NavigationView navigationView = findViewById(R.id.nav_view);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_Account_admin:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new ProfileAdminFragment()).commit();
                        break;
                    case R.id.nav_Games:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new GamesAdminFragment()).commit();

                        break;
                    case R.id.nav_Users:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new AllUsersAdminFragment()).commit();

                        break;
                    case R.id.nav_Fixture:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new FixturesAdminFragment()).commit();

                        break;
                    case R.id.nav_Gallery:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new AdminFragmentGallery()).commit();

                        break;

                    case R.id.nav_News:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new NewsAdminFragment()).commit();
                        break;
                    case R.id.nav_Lotto:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new AdminFragmentLotto()).commit();
                        break;
                    case R.id.nav_Contact:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new ContactAdminFragment()).commit();

                        break;
                    default:

                }
                draw.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, draw, toolbar,
                R.string.nav_app_bar_open_drawer_description, R.string.navigation_drawer_close);
        draw.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new ProfileAdminFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_Account_admin);
        }

    }


    @Override
    public void onBackPressed() {
        if (draw.isDrawerOpen(GravityCompat.START)) {
            draw.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    public void logoutAdmin(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }


    public void setTraining(View view) {
        {
        }
    }

    public void doLogoutAdmin(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }





}