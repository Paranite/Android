package com.example.kontrolinis1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    Button atverti;
    Button ieskoti;
    Button uzdaryti;
    TextView isved_lauk;
    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isved_lauk = findViewById(R.id.text_isv);
        atverti = findViewById(R.id.buttonActivity2);
        ieskoti = findViewById(R.id.buttonSearch);
        uzdaryti = findViewById(R.id.buttonClose);

    }

    public void openSecondActivity(View v){
        Intent newIntent = new Intent(MainActivity.this, MainActivity2.class);
        startActivityForResult(newIntent, 1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String tekstas = data.getStringExtra("tekstas");
        String laikas = data.getStringExtra("laikas");
        isved_lauk.setText(tekstas + " " + laikas);
    }

    public void uzdaryti(View v) {
        finish();
        System.exit(0);
    }

    public void searchField(View v){
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, isved_lauk.getText());
        startActivity(intent);
    }
}