package com.example.kontrolinisvar4;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class FragmentGraph extends Fragment {


    static GraphView graph;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_graph, container, false);

        graph = (GraphView) view.findViewById(R.id.graph);
        registerForContextMenu(graph);
        return view;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.click_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.btn_apie_programa:
                Intent iAP = new Intent(getActivity(), AboutProgramActivity.class);
                startActivity(iAP);
                return true;

            case R.id.btn_kaip_naudotis:
                Intent iHU = new Intent(getActivity(), HowToUseActivity.class);
                startActivity(iHU);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }


    public static void redrawGraph (float k, float b, float start, float end){
        graph.removeAllSeries();
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>();;
        if (MainActivity.function.equals("tiesine")){
            for(float i = start; i <= end; i++){
                DataPoint point = new DataPoint(i, k*i+b);
                series.appendData(point, false, 20);
            }
            graph.addSeries(series);
        }
    }

}