package com.example.contactsapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Call_activity extends AppCompatActivity {
Button btn_0,btn_1,btn_2,btn_3,btn_4,btn_5,btn_6,btn_7,btn_8,btn_9,call;
String number="";
EditText et_call;
ImageButton remove_num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        et_call=findViewById(R.id.tv_call);
        call=findViewById(R.id.btn_call);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_call.getText().toString().length()<11){
                    Toast.makeText(Call_activity.this, R.string.please_add_a_valid_number, Toast.LENGTH_SHORT).show();
                }else{
                String phoneNumber = "tel:" + et_call.getText().toString();
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(phoneNumber));
                startActivity(intent);
            }}

        });
    }
}