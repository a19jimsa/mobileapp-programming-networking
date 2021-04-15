package com.example.networking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Mountain> mountains = new ArrayList<Mountain>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mountains.add(new Mountain("Berg", 1200));
        mountains.add(new Mountain("Berg", 1200));
        mountains.add(new Mountain("Berg", 1200));
        ArrayAdapter<Mountain> adapter = new ArrayAdapter<Mountain>(this, R.layout.list_item_textview,R.id.list_item_text, mountains);
        ListView my_listView = findViewById(R.id.my_listView);
        my_listView.setAdapter(adapter);
        my_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Hejsan", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
