package com.example.myapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button secondActivity = (Button) findViewById(R.id.scndActivity);
        secondActivity.setOnClickListener(v -> {
            Intent startIntent = new Intent(getApplicationContext(), SecondActivity.class);
            startIntent.putExtra("minePackage", "Hello World!");
            startActivity(startIntent);
        });

        Button googleBtn = findViewById(R.id.frstActivity);
        googleBtn.setOnClickListener(v -> {
            String searchQuery = "search query"; // Replace with your desired search query
            Uri searchUri = Uri.parse("https://www.google.com/search?q=" + Uri.encode(searchQuery));

            Intent searchIntent = new Intent(Intent.ACTION_VIEW, searchUri);

            if (searchIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(searchIntent);
            } else {
                Log.e("MainActivity", "No Intent available to handle action");
            }
        });
    }
}