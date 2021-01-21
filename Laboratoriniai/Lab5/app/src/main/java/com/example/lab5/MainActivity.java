package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    static FragmentManager FM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FM = getSupportFragmentManager();
    }

    public static void withA(String string){
        Bundle bundle = new Bundle(); // grupe kint
        bundle.putString("text", string);
        WithA fragment = new WithA();
        fragment.setArguments(bundle);
        FragmentTransaction FT = FM.beginTransaction();
        FT.add(R.id.frame, fragment);
        FT.addToBackStack("back");
        FT.commit();
    }

    public static void withoutA(String string){
        Bundle bundle = new Bundle();
        bundle.putString("text", string);
        WithoutA fragment = new WithoutA();
        fragment.setArguments(bundle);
        FragmentTransaction FT = FM.beginTransaction();
        FT.add(R.id.frame, fragment);
        FT.addToBackStack("back");
        FT.commit();
    }

}