package edu.itsligo.gaaappmain;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Button logout = findViewById(R.id.logoutBtn);
        FloatingActionButton login = findViewById(R.id.floatingActionButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        });
        // bottom nav
        BottomNavigationView botomNav = findViewById(R.id.bottom_navigation);
        botomNav.setOnNavigationItemSelectedListener(navListerner);

        getSupportFragmentManager().beginTransaction().replace(R.id.homeFagment_container, new HomeFragmentHome()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListerner =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new HomeFragmentHome();
                            break;
                        case R.id.nav_news:
                            selectedFragment = new HomeFragmentNews();
                            break;
                        case R.id.nav_homeContact:
                            selectedFragment = new HomeFragmentContact();
                            break;

                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.homeFagment_container,selectedFragment).commit();
                    return true; // true means you want to selected a selected item
                }
            };


    public void DoLogin(View view) {
    }
}