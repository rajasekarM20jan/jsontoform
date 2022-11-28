package com.example.jsontoform;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

import adapter.ListAdapter;
import model.DynamicForm;

public class MainActivity extends AppCompatActivity {

    ArrayList myViews,abcd ;
    LinearLayout linearLayout;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*
        lv=findViewById(R.id.listView);
*/

        linearLayout=findViewById(R.id.linearLayout);

        abcd=new ArrayList<>();
        getData();
       /* myViews=new ArrayList<View>();
        getData2();*/



    }

    private void getData2() {
        try {
            InputStream iStream = getAssets().open("data.json");
            int size = iStream.available();
            byte[] buffer = new byte[size];
            iStream.read(buffer);
            iStream.close();
            String myString = new String(buffer, "UTF-8");
            JSONObject jobj = new JSONObject(myString);
            JSONObject robj = jobj.getJSONObject("rObj");
            JSONObject getFinancialQuestionnarie = robj.getJSONObject("getFinancialQuestionnarie");
            JSONArray forms = getFinancialQuestionnarie.getJSONArray("forms");
            for(int i=0;i<forms.length();i++){
                JSONObject form=forms.getJSONObject(0);
                JSONArray form1=form.getJSONArray("form");
                JSONObject index0= form1.getJSONObject(0);
                JSONArray questions=index0.getJSONArray("questions");
                for(int j=0;j<questions.length();j++){
                    DynamicForm df=new DynamicForm(MainActivity.this);
                    JSONObject questionIndex=questions.getJSONObject(j);
                    String type= questionIndex.getString("type");
                    View view=new View(MainActivity.this);
                    switch (type){
                        case "text":
                        case "subquestions": {
                            view=df.getEditText();
                            break;
                        }
                        case "number":{
                            view=df.getEditTextNumber();
                            break;
                        }
                        case "textArea":{
                            view=df.getEditTextArea();
                            break;
                        }
                        case "radio":{
                            RadioGroup rg=df.getRadioGroup();
                            JSONArray radioArray=questionIndex.getJSONArray("value");
                            for(int k=0;k<radioArray.length();k++){
                                JSONObject radioIndex=radioArray.getJSONObject(k);
                                RadioButton radioButton=df.getRadioButton();
                                radioButton.setText(radioIndex.getString("value"));
                                rg.addView(radioButton);
                            }
                            view=rg;
                            break;
                        }
                        case "checkbox":{
                            CheckBox cb=df.getCheckBox();
                            JSONArray value=questionIndex.getJSONArray("value");
                            JSONObject aj=value.getJSONObject(0);
                            String val=aj.getString("value");
                            cb.setText(val);
                            view=cb;
                            break;
                        }
                        case "label":{
                            JSONArray qArray=questionIndex.getJSONArray("questions");
                            for(int k=0;k<qArray.length();k++){
                                JSONObject qIndex=qArray.getJSONObject(k);
                                String questionType=qIndex.getString("type");
                                switch (questionType){
                                    case "text":
                                    case "number":{
                                        view=df.getEditText();
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    myViews.add(view);
                }
            }

            System.out.println("myViewsArray : "+myViews);

            ListAdapter adapter=new ListAdapter(MainActivity.this,R.layout.custom_list,myViews);
            lv.setAdapter(adapter);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    private void getData() {
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
            for(int i=0;i<forms.length();i++){
                JSONObject formIn1=(JSONObject) forms.get(i);
                JSONArray form1=formIn1.getJSONArray("form");
                JSONObject index0= form1.getJSONObject(0);
                JSONArray questions=index0.getJSONArray("questions");
                System.out.println("qwertyuiop"+questions);
                for(int j=0;j<questions.length();j++){
                    LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
                    params.setMargins(20,20,20,20);
                    JSONObject questionIndex=questions.getJSONObject(j);
                    String type= questionIndex.getString("type");
                    View view;
                    view=new View(MainActivity.this);
                    switch (type){
                        case "text":{
                            TextView et=new TextView(MainActivity.this);
                            et.setText(questionIndex.getString("questionLabel"));
                            et.setLayoutParams(params);
                            view=et;
                            view.setTag("text");
                            linearLayout.addView(view);
                            EditText et1=new EditText(MainActivity.this);
                            et1.setBackground(getResources().getDrawable(R.drawable.edit_text_bg));
                            et1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                            view=et1;
                            break;
                        }
                        case "radio":{
                            TextView tv=new TextView(MainActivity.this);
                            tv.setText(questionIndex.getString("questionLabel"));
                            tv.setLayoutParams(params);
                            view=tv;
                            view.setTag("text");
                            linearLayout.addView(view);
                            RadioGroup r=new RadioGroup(MainActivity.this);
                            r.setGravity(Gravity.START);
                            JSONArray radioArray=questionIndex.getJSONArray("value");
                            for(int k=0;k<radioArray.length();k++){
                                JSONObject radioIndex=radioArray.getJSONObject(k);
                                RadioButton radioButton=new RadioButton(MainActivity.this);
                                radioButton.setText(radioIndex.getString("value"));
                                r.addView(radioButton);
                            }
                            view=r;
                            break;
                        }
                        case "label":{
                            TextView tv=new TextView(MainActivity.this);
                            tv.setText(questionIndex.getString("questionLabel"));
                            tv.setLayoutParams(params);
                            view=tv;
                            view.setTag("TextView");
                            linearLayout.addView(view);
                            JSONArray qArray=questionIndex.getJSONArray("questions");
                            for(int k=0;k<qArray.length();k++){
                                JSONObject qIndex=qArray.getJSONObject(k);
                                String questionType=qIndex.getString("type");
                                switch (questionType){
                                    case "text":
                                    case "number":{
                                        TextView tv2=new TextView(MainActivity.this);
                                        tv2.setText(qIndex.getString("questionLabel"));
                                        tv2.setLayoutParams(params);
                                        view=tv2;
                                        view.setTag("TextView");
                                        linearLayout.addView(view);
                                        EditText et2=new EditText(MainActivity.this);
                                        et2.setBackground(getResources().getDrawable(R.drawable.edit_text_bg));
                                        et2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                        view=et2;
                                        linearLayout.addView(view);
                                        break;
                                    }
                                }
                            }
                        }
                        case "number":{
                            TextView et=new TextView(MainActivity.this);
                            et.setText(questionIndex.getString("questionLabel"));
                            et.setLayoutParams(params);
                            view=et;
                            view.setTag("number");
                            linearLayout.addView(view);
                            EditText et1=new EditText(MainActivity.this);
                            et1.setBackground(getResources().getDrawable(R.drawable.edit_text_bg));
                            et1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                            view=et1;
                            break;
                        }
                        case "textArea":{
                            TextView et=new TextView(MainActivity.this);
                            et.setText(questionIndex.getString("questionLabel"));
                            et.setLayoutParams(params);
                            view=et;
                            view.setTag("textArea");
                            linearLayout.addView(view);
                            EditText et1=new EditText(MainActivity.this);
                            et1.setHeight(400);
                            et1.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                            et1.setBackground(getResources().getDrawable(R.drawable.edit_text_bg));
                            view=et1;
                            break;
                        }
                        case "subquestions" :{
                            TextView et=new TextView(MainActivity.this);
                            et.setText(questionIndex.getString("questionLabel"));
                            et.setLayoutParams(params);
                            view=et;
                            view.setTag("textArea");
                            linearLayout.addView(view);
                            EditText et1=new EditText(MainActivity.this);
                            et1.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                            et1.setBackground(getResources().getDrawable(R.drawable.edit_text_bg));
                            view=et1;
                            break;
                        }
                        case "checkbox":{
                            CheckBox cb=new CheckBox(MainActivity.this);
                            JSONArray value=questionIndex.getJSONArray("value");
                            JSONObject aj=value.getJSONObject(0);
                            String val=aj.getString("value");
                            cb.setText(val);
                            view=cb;
                            break;
                        }
                    }
                    linearLayout.addView(view);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}