package com.example.lab5;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;


public class List extends Fragment {

    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list, container, false);
        listView = (ListView) view.findViewById(R.id.listView);
        final ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("aaaaaaaaa");
        arrayList.add("Hello how are you doing");
        arrayList.add("AbsaaaAsdb");
        arrayList.add("Till Next Time");
        // arrayadapter priskiria komponenta arraylist view'e esanciam list
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, arrayList);

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), arrayList.get(i), Toast.LENGTH_SHORT).show();
                fragmentas(arrayList.get(i));
                }
            });
        return view;
    }

    private void fragmentas(String string){
        if(string.indexOf('a') >= 0 || string.indexOf('A') >= 0){
            MainActivity.withA(string);
        }
        else{
            MainActivity.withoutA(string);
        }
    }
}