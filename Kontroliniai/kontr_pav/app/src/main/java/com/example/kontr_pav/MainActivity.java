package com.example.kontr_pav;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity{

    TextView isved_lauk;
    RandomNum numGenerator = new RandomNum();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isved_lauk = findViewById(R.id.isved_lauk);
    }

    public void startGeneration(View v) {
        int activator = v.getId();

        switch(activator){
            case R.id.mygtukas1:
                startTimerThread(false);
                break;
            case R.id.mygtukas3:
                startTimerThread(true);
                break;
        }
    }

    public void startTimerThread(boolean positives){
        StopRunningThread();
        if (positives){
            numGenerator = new RandomNum(100, 0, isved_lauk);
        }
        else{
            numGenerator = new RandomNum(0, -100, isved_lauk);
        }
//        numGenerator.run();
        new Thread(numGenerator).start();
    }

    public void StopRunningThread(){
        if(numGenerator.isRunning()){
            numGenerator.stop();
        }
    }

    public void handleEndOfGeneration(View v){
        StopRunningThread();
        String lastGenerated = isved_lauk.getText().toString();
        SearchForNumber(lastGenerated);
    }

    public void SearchForNumber(String lastGenerated){
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, lastGenerated);
        startActivity(intent);
    }

    class RandomNum implements Runnable {

        AtomicBoolean running = new AtomicBoolean(false);
        int max;
        int min;
        int range;
        int rand;
        TextView isved_lauk;
//        final Handler handler = new Handler();

        RandomNum(){}

        RandomNum(int max, int min, TextView isved_lauk) {
            this.max = max;
            this.min = min;
            this.range = max - min + 1;
            this.isved_lauk = isved_lauk;
        }

        public boolean isRunning(){
            return running.get();
        }

        public void stop(){
            running.set(false);
        }

        public void run() {
            running.set(true);

            while (running.get()) {
                rand = (int) (Math.random() * range) + min;

//                handler.post(new Runnable(){
//                    public void run() {
//                        isved_lauk.setText(String.valueOf(rand));
//                    }
//                });
                runOnUiThread(new Runnable() {
                    @Override
                    public void  run(){
                        isved_lauk.setText(String.valueOf(rand));
                    }
                });
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}