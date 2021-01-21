package com.example.kontrolinisvar4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ButtonFragment2 extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_button2, container, false);
            Button buttonEbay = (Button) view.findViewById(R.id.buttonEbay);
        buttonEbay.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    toEbay();
                }
        });

        Button buttonAmazon = (Button) view.findViewById(R.id.buttonAmazon);
        buttonAmazon.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                toAmazon();
            }
        });


        return view;
    }

    public void toEbay(){
        WebViewFragment.setWeb("https://www.ebay.com");
    }

    public void toAmazon(){
        WebViewFragment.setWeb("https://www.amazon.com");
    }
}