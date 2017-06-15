package com.example.appconimagenes;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Product> arrayList;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // ver listview_de xml
        super.onCreate(savedInstanceState);
        //localizar la lista en la actividad principañ
        setContentView(R.layout.activity_main);

        arrayList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.listView);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, Second_activity.class);
                intent.putExtra("Character",lv.getItemIdAtPosition(i)).toString(); //check to string
                //intent.putExtra
                intent.putExtra("jsonArray",arrayList.toString());
                startActivity(intent);
            }
        });


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadJSON().execute("http://randominteractive.net/TEST/data.json");//"http://quocnguyen.16mb.com/products.json");
            }
        });
    }
    // Execute DownloadJSON AsyncTask
    class ReadJSON extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            return readURL(params[0]);
        }

        @Override
        protected void onPostExecute(String content) {
            try {
                //JSONObject jsonObject = new JSONObject(content);
                //JSONArray jsonArray =  jsonObject.getJSONArray("products");
// Crear el arreglo
                JSONArray jsonArray = new JSONArray(content);

                for(int i =0;i<jsonArray.length(); i++){
                    JSONObject productObject = jsonArray.getJSONObject(i);
                    //objetos en un arrglo
                    arrayList.add(new Product(
                            productObject.getString("url"),
                            productObject.getString("titutlo"),
                            //productObject.getString("detalle"),
                            productObject.getString("texto")
                    ));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            CustomListAdapter adapter = new CustomListAdapter(
                    getApplicationContext(), R.layout.custom_list_layout, arrayList
            );
            lv.setAdapter(adapter);
        }
    }


    private static String readURL(String theUrl) {
        StringBuilder content = new StringBuilder();
        try {
            // create a url object
            URL url = new URL(theUrl);
            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();
            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
