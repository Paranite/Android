package com.example.kontrolinisvar4;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

//    static FragmentManager FM;
    static String function;
    TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        FM = getSupportFragmentManager();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        info = findViewById(R.id.textView_info);
        registerForContextMenu(info);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.click_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.btn_apie_programa:
                Intent iAP = new Intent(MainActivity.this, AboutProgramActivity.class);
                startActivity(iAP);
                return true;

            case R.id.btn_kaip_naudotis:
                Intent iHU = new Intent(MainActivity.this, HowToUseActivity.class);
                startActivity(iHU);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
            if  (id == R.id.btn_tieses_funkcijos) {
//                ButtonFragment1 fragment = new ButtonFragment1();
//                FragmentTransaction FT = FM.beginTransaction();
//                FT.add(R.id.frame, fragment);
//                FT.commit();
                Intent intent = new Intent(MainActivity.this, FunctionActivity.class);
                function = "tiesine";
                startActivity(intent);
            }
            else if (id == R.id.btn_laipsnine_funkcija) {
//            ButtonFragment2 fragment = new ButtonFragment2();
//            FragmentTransaction FT = FM.beginTransaction();
//            FT.add(R.id.frame, fragment);
//            FT.commit();
                Intent intent = new Intent(MainActivity.this, FunctionActivity.class);
                startActivity(intent);
        }
            else if (id == R.id.button_close) {
            finishAffinity();
            System.exit(0);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}