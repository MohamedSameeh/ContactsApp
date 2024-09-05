package com.example.contactsapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class add_contact extends AppCompatActivity {
EditText tv_contact_name,tv_contact_number;
Button add_contact_btn;
MyDataBase dataBase;
ImageButton eg_img_btn,eng_img_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoadLanguage(this);
        setContentView(R.layout.activity_add_contact);
        dataBase=new MyDataBase(this);
        eg_img_btn=findViewById(R.id.eg_img_btn);
        eng_img_btn=findViewById(R.id.america_img_btn);
        tv_contact_name=findViewById(R.id.tv_contact_name);
        tv_contact_number=findViewById(R.id.tv_contact_number);
        add_contact_btn=findViewById(R.id.add_contact_btn);
        add_contact_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=tv_contact_name.getText().toString().trim();
                String number=tv_contact_number.getText().toString().trim();
                if(number.length()<11){
                    Toast.makeText(add_contact.this, R.string.please_add_a_valid_number, Toast.LENGTH_SHORT).show();
                }else{
                Contacts contacts=new Contacts(name,number);
                long result = dataBase.addData(contacts);
                Intent i=new Intent(add_contact.this,MainActivity.class);
                startActivity(i);
            }}
        });
        eg_img_btn.setOnClickListener(v->{
            SetLocale("ar");
            recreate();
        });
        eng_img_btn.setOnClickListener(v->{
            SetLocale("en");
            recreate();
        });
    }
    void SetLocale(String lang){
        Locale locale=new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration=getResources().getConfiguration();
        configuration.setLocale(locale);
        getResources().updateConfiguration(configuration,new DisplayMetrics());
        SetLanguage(lang);
    }
    void SetLanguage(String lang){
        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("SelectedLanguage",lang);
        editor.apply();
    }
    void LoadLanguage(Context context){
        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(context);
        String lang=sp.getString("SelectedLanguage","en");
        SetLocale(lang);
    }

}