package com.example.lab5;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class WithoutA extends Fragment {

    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_without_a, container, false);
        Bundle bundle = this.getArguments();
        String string = bundle.getString("text");
        textView = view.findViewById(R.id.textViewWA);

        int vowels = 0;
        int upper_letters = 0;

        for(int i = 0; i < string.length(); i++){
            char temp = string.charAt(i);
            if(Character.isUpperCase(temp)) upper_letters++;
            temp = Character.toLowerCase(temp);
            if(temp == 'a' || temp == 'e' || temp == 'i' || temp == 'o' || temp == 'u') vowels++;
        }

        textView.setText("Zodyje yra " + string.length() + " raides\n" + vowels +" balses\n"
                + upper_letters + " didziosios raides\n" + (string.length() - upper_letters) + " mazosios raides");
        return view;
    }
}