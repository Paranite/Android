package com.example.kontrolinisvar4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FragmentFunction extends Fragment {

    EditText kf;
    EditText bf;
    EditText startf;
    EditText endf;
    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_function, container, false);

        kf = view.findViewById(R.id.func_k);
        bf = view.findViewById(R.id.func_b);
        startf = view.findViewById(R.id.func_int_start);
        endf = view.findViewById(R.id.func_int_end);

        button = (Button) view.findViewById(R.id.btn_skaiciuoti);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                float k = Float.parseFloat(kf.getText().toString());
                float b = Float.parseFloat(bf.getText().toString());
                float start = Float.parseFloat(startf.getText().toString());
                float end = Float.parseFloat(endf.getText().toString());

                FragmentGraph.redrawGraph(k, b, start, end);
            }
        });

        return view;
    }


}