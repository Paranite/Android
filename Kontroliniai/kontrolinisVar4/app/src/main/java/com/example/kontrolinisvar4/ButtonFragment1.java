package com.example.kontrolinisvar4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;

public class ButtonFragment1 extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_button1, container, false);
        Button buttonGoogle = (Button) view.findViewById(R.id.buttonGoogle);
        buttonGoogle.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                toGoogle();
            }
        });

        Button buttonYoutube = (Button) view.findViewById(R.id.buttonYoutube);
        buttonYoutube.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                toYoutube();
            }
        });


        return view;
    }

    public void toGoogle(){
        WebViewFragment.setWeb("https://www.google.com");
    }

    public void toYoutube(){
        WebViewFragment.setWeb("https://www.youtube.com");
    }

}