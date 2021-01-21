package com.example.lab5;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class WithA extends Fragment {

    TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_with_a, container, false);
        Bundle bundle = this.getArguments();
        String string = bundle.getString("text");
        textView = view.findViewById(R.id.textViewA);

        int count = 0;
        for(int i = 0; i < string.length(); i++){
            char temp = string.charAt(i);
            if(temp == 'a' || temp == 'A') count++;
        }
        textView.setText("Zodyje - " + string + " yra " + count + " a raides");

        return view;
    }
}