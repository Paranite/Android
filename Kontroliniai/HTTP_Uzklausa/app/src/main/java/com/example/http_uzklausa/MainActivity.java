package com.example.http_uzklausa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void siusti_HTTP_uzklausa(View view) {

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        //String url ="https://www.delfi.lt/keliones/platus-pasaulis/nauja-turizmo-era-anksciau-apie-tokias-keliones-galejome-tik-pasvajoti.d?id=85813089";
        //String url = "https://scholar.google.lt/scholar?cluster=14858779363383446640&hl=lt&as_sdt=0,5#d=gs_cit&u=%2Fscholar%3Fq%3Dinfo%3AcAxgFhv9NM4J%3Ascholar.google.com%2F%26output%3Dcite%26scirp%3D0%26scfhb%3D1%26hl%3Dlt";
        //String url = "https://eismoinfo.lt/#!/";
        // http://date.jsontest.com/
        // https://www.jsontest.com/

        String url = "http://date.jsontest.com/";

        //String url = "https://www.jsontest.com/"; // Veikianti nuoroda




        final TextView textView = (TextView) findViewById(R.id.textView2);
// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            //String db_updated = jsonObject.getString("date");
                            //String db_updated = jsonObject.getString("date");
                            String db_updated = jsonObject.getString("time");
                            textView.setText("Data: " + db_updated);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //textView.setText(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!" + error.toString());
                Log.i("klaida", "That didn't work!" + error.toString());
            }
        });
        queue.add(stringRequest);

    }

    public void siusti_HTTP_uzklausa1(View view) {

        final TextView textView = findViewById(R.id.textView2);
        textView.setText("Socket užklausa vykdoma" +
                "\nIšsiūsta į http://api.openweathermap.org" +
                "\nReikia palaukti kelias minutes");

        new Thread(new Runnable() {
            public void run() {

                try {
                    String hostname = "api.openweathermap.org";
                    int port = 80;

                    InetAddress addr = InetAddress.getByName(hostname);
                    Socket socket = new Socket(addr, port);

                    // Send headers
                    BufferedWriter wr =
                            new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF8"));
                    wr.write("GET http://api.openweathermap.org/data/2.5/weather?q=Vilnius&appid=50b041844551c93691d4a31a855b16f5 HTTP/1.1\r\n");
                    wr.write("User-Agent: belekas\r\n");
                    wr.write("Host: " + hostname + "\r\n");
                    wr.write("\r\n");

                    wr.flush();

                    // Get response
                    BufferedReader rd = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String line;

                    final String response = "";
                    System.out.println(response);
                    String lastLine = "";
                    while ((line = rd.readLine()) != null) {
                        Log.d("tagas", line);
                        response.concat(line + "\n");
                        lastLine = line;

                    }
                    final String lineToShow = lastLine;
//                            System.out.println(response);
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            final TextView textView = findViewById(R.id.textView2);
                            //textView.setText(lineToShow);
                            try {
                                JSONObject jsonObject = new JSONObject(lineToShow);
                                //String db_updated = jsonObject.getString("date");
                                String cityName = jsonObject.getString("name");
                                //String humidity = jsonObject.getString("humidity");
                                String main = jsonObject.getString("main");
                                JSONObject jsonObject1 = new JSONObject(main);

                                String temp = jsonObject1.getString("temp");
                                String feels_like = jsonObject1.getString("feels_like");
                                String pressure = jsonObject1.getString("pressure");
                                String humidity = jsonObject1.getString("humidity");

                                DecimalFormat df = new DecimalFormat("###.###");
                                String db_updated = "Miestas: " + cityName + "\nPagrindiniai duomenys:"
                                        + "\nTemperatūra: " + df.format(Float.parseFloat(temp) - 273)+ " C°" +
                                        "\nJuntamoji temperatūra: " + df.format(Float.parseFloat(feels_like) - 273) + " C°" +
                                        "\nSlėgis: " + pressure +
                                        "\nDrėgnis: " + humidity +
                                        "\n------------------------" +
                                        "\nVisas užklausos rezultatas: " + jsonObject;
                                //textView.setText("Data: " + db_updated); // Parodo vis1 gaut1 eilute
                                Log.d("tagasm", db_updated);
                                textView.setText(db_updated);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });


                    wr.close();
                    rd.close();
                } catch (Exception e) {
                    Log.d("tagas", e.toString());
                }
            }
        }).start();
    }

}