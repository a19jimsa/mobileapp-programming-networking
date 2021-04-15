package com.example.networking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String name = extras.getString("name");
            int height = extras.getInt("height");
            String location = extras.getString("location");
            // Do something with the name and number
            Log.d("a19jimsa", name+height+location);
            TextView nameView = findViewById(R.id.textView_name);
            nameView.setText("Name: " + name);
            TextView heightView = findViewById(R.id.textView_height);
            heightView.setText("Height: "+height);
            TextView locationView = findViewById(R.id.textView_location);
            locationView.setText("Location: "+location);
        }
        Button button = findViewById(R.id.back_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}