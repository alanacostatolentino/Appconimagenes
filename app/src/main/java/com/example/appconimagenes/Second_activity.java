package com.example.appconimagenes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

public class Second_activity extends AppCompatActivity {
    ImageView imageView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_activity);

        imageView1 = (ImageView) findViewById(R.id.imageView1);
        Picasso.with(getApplicationContext()).load("http://randominteractive.net/TEST/img/img1.png").into(imageView1);


        Bundle bundle = getIntent().getExtras();

        Intent intent = getIntent();
        String jsonArray= intent.getStringExtra("jsonArray");

        //JSONObject jsonObject = new JSONObject(content);
        try {
            JSONArray array = new JSONArray(jsonArray);
            System.out.println(array.toString(2));
        } catch (JSONException e1) {
            e1.printStackTrace();
        }


    }
}

