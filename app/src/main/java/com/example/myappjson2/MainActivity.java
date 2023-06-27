package com.example.myappjson2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import WebService.Asynchtask;
import WebService.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void btVer (View view){

        Bundle bundle = this.getIntent().getExtras();
        Map<String, String> infor = new HashMap<String, String>();
        WebService as= new WebService(" https://jsonplaceholder.typicode.com/users"
                ,infor, MainActivity.this, MainActivity.this);
        as.execute("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        TextView refe= (TextView) findViewById(R.id.txtMJson);
        String respuesta=" ";
        JSONArray vInfJson= new JSONArray(result);

        for (int i = 0; i < vInfJson.length(); i++) {
            JSONObject use = vInfJson.getJSONObject(i);
            String nomb = use.getString("name");
            String nUse = use.getString("username");
            String dir= use.getString("email");
            String cWeb = use.getString("website");
            respuesta= respuesta+ " (" + i +") "+
                    " Su nombre = " +  " " + nomb +"\n"+
                    " Su usuario = " +",  " + nUse+",  "+"\n"+
                    " Su direccion = "  + dir+",  "+cWeb+"\n";
            final int CONT = i;
            final String fUSUARIO = respuesta;
            new android.os.Handler().postDelayed(new Runnable() {
                public void run() {
                    refe.setText(fUSUARIO);

                    if (CONT < vInfJson.length() - 1) {
                        refe.append("\n");
                    }
                }
            }, (i + 1) * 10);
        }


    }
}