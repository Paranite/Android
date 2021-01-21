package com.example.kontrolinisvar4;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    static FragmentManager FM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FM = getSupportFragmentManager();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
            if  (id == R.id.button_pirmi) {
                ButtonFragment1 fragment = new ButtonFragment1();
                FragmentTransaction FT = FM.beginTransaction();
                FT.add(R.id.frame, fragment);
                FT.commit();
            }
            else if (id == R.id.button_antri) {
            ButtonFragment2 fragment = new ButtonFragment2();
            FragmentTransaction FT = FM.beginTransaction();
            FT.add(R.id.frame, fragment);
            FT.commit();
        }
            else if (id == R.id.button_close) {
            finishAffinity();
            System.exit(0);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}