package edu.itsligo.gaaappmain.Games;

import android.os.Bundle;

import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import edu.itsligo.gaaappmain.HomeFragmentHome;
import edu.itsligo.gaaappmain.R;

public class HomeGamesStats extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_games_stats);
        getSupportFragmentManager().beginTransaction().replace(R.id.Home_Games_fragment_Container,
                new HomeFragmentFixture()).commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_Games_navagation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragement = null;

                switch (item.getItemId()){
                    case R.id.nav_Home_Fixture:
                       selectedFragement = new HomeFragmentFixture();
                        break;
                    case R.id.nav_Home_Results:
                        selectedFragement = new HomeFragmentResults();
                        break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.Home_Games_fragment_Container,
                        selectedFragement).commit();
                return true;
            }


        });
    }
}