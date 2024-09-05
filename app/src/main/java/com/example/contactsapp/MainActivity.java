package com.example.contactsapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton floating_btn,floating_call_btn;
    RecyclerView recyclerView;
    MyDataBase dataBase;
    ArrayList<Contacts> contactsList;
    RecyclerViewHolder adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoadLanguage(this);
        setContentView(R.layout.activity_main);


        floating_btn = findViewById(R.id.floating_btn);
        recyclerView = findViewById(R.id.recyclerView);
        floating_call_btn=findViewById(R.id.floating_call_btn);
        dataBase = new MyDataBase(this);
        contactsList = dataBase.getAllContacts();

        adapter = new RecyclerViewHolder(this, contactsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        floating_btn.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, add_contact.class);
            startActivity(i);
        });
        floating_call_btn.setOnClickListener(v->{
            Intent i=new Intent(MainActivity.this, Call_activity.class);
            startActivity(i);
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
        SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("SelectedLanguage",lang);
        editor.apply();
    }
    void LoadLanguage(Context context){
        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
        String lang=sp.getString("SelectedLanguage","en");
        SetLocale(lang);
    }
}
