package com.example.lab6;

import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.ContextMenu;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.TimePicker;

import java.nio.channels.FileLockInterruptionException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    TextView pirmas;
    TextView antras;
    TextView trecias;
    Context context = this;
    String currentText;
    TextThread t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pirmas = findViewById(R.id.textView1);
        antras = findViewById(R.id.textView2);
        trecias = findViewById(R.id.textView3);

        registerForContextMenu(pirmas); // palaikius kelias s atidarys meniu
        registerForContextMenu(antras);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        currentText = ((TextView) v).getText().toString();
        getMenuInflater().inflate(R.menu.click_menu, menu);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemSimbSk:
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("Alert");
                alert.setMessage("Simboliu sk. - " + currentText.length());

                alert.create().show();

                antras.setText("Radau " + currentText.length() + " simboliu");
                return true;

            case R.id.itemSimbRas:
                t = new TextThread();
                t.start();
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.submeniu){
            Log.i("log", "Clicked submenu");
            Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                    SimpleDateFormat format = new SimpleDateFormat("HH:mm");
//                    Date time = new Date();
//                    Date date1 = null;
//                    Date date2 = null;
//                    try{
//                        date1 = format.parse(time.getHours() + ":" + time.getMinutes());
//                        date2 = format.parse(hourOfDay + ":" + minute);
//                    } catch (ParseException e){
//                        e.printStackTrace();
//                    }
//                    long difference = Math.abs(date2.getTime() - date1.getTime());
                    Date time = new Date();
                    long difference = Math.abs((time.getHours()*60+time.getMinutes()) - (hourOfDay*60 + minute));
                    AlertDialog.Builder alert = new AlertDialog.Builder(context);
                    alert.setTitle("Alert");
                    alert.setMessage("Skirtumas tarp laiku - " + difference + "min"); // difference/1000/60

                    alert.create().show();

                    pirmas.setText("Skirtumas tarp laiku - " + difference + "min");
                }
            }, hour, minute, true);
            timePickerDialog.show();
        }

        return super.onOptionsItemSelected(item);
    }

    private void setText(final TextView text, final String value){
        runOnUiThread(new Runnable(){
            @Override
            public void run(){
                text.setText(value);
            }
        });
    }

    class TextThread extends Thread{
        TextThread(){}

        public void run(){
            int i = 0;
            while(true){
                if(i == currentText.length()) return;
                setText(trecias, currentText.substring(i, i+1));
                try{
                    TimeUnit.MILLISECONDS.sleep(500);
                    i++;
                } catch (InterruptedException e){
                    return;
                }
            }
        }
    }

    public void close(MenuItem item){
        finishAffinity();
        System.exit(0);
    }
}