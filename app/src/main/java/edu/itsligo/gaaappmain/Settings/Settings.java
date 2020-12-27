package edu.itsligo.gaaappmain.Settings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import edu.itsligo.gaaappmain.R;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}