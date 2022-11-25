package com.example.jsontoform;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            InputStream iStream= getAssets().open("data.json");
            int size = iStream.available();
            byte[] buffer = new byte[size];
            iStream.read(buffer);
            iStream.close();

            String myString= new String(buffer,"UTF-8");

            JSONObject jobj=new JSONObject(myString);
            
            JSONObject robj=jobj.getJSONObject("rObj");

            JSONObject getFinancialQuestionnarie=robj.getJSONObject("getFinancialQuestionnarie");

            JSONArray forms=getFinancialQuestionnarie.getJSONArray("forms");

            JSONObject formIn1=(JSONObject) forms.get(0);

            JSONArray form1=formIn1.getJSONArray("form");

            System.out.println(form1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}