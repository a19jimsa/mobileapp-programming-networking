package com.example.networking;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Mountain> mountains;
    private ArrayAdapter<Mountain> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mountains = new ArrayList<Mountain>();
        adapter = new ArrayAdapter<Mountain>(this, R.layout.list_item_textview,R.id.list_item_text, mountains);
        ListView my_listView = findViewById(R.id.my_listView);
        my_listView.setAdapter(adapter);
        my_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Mountain mountain = adapter.getItem(position);
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("name", mountain.getName());
                intent.putExtra("height", mountain.getHeight());
                intent.putExtra("location", mountain.getLocation());
                startActivity(intent);
            }
        });
        new JsonTask().execute("https://wwwlab.iit.his.se/brom/kurser/mobilprog/dbservice/admin/getdataasjson.php?type=brom");
    }
    @SuppressLint("StaticFieldLeak")
    private class JsonTask extends AsyncTask<String, String, String> {

        private HttpURLConnection connection = null;
        private BufferedReader reader = null;

        protected String doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null && !isCancelled()) {
                    builder.append(line).append("\n");
                }
                return builder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String json) {
            Log.d("TAG", json.toString());
            try {
                // Ditt JSON-objekt som Java
                JSONArray jsonarray = new JSONArray(json);
                for(int i = 0; i < jsonarray.length(); i++){
                    JSONObject jsonObject = jsonarray.getJSONObject(i);
                    String name = jsonObject.getString("name");
                    int height = jsonObject.getInt("size");
                    String location = jsonObject.getString("location");
                    mountains.add(new Mountain(name, height, location));
                    adapter.notifyDataSetChanged();
                }

            } catch (JSONException e) {
                Log.e("brom","E:"+e.getMessage());
            }
        }
    }
}

