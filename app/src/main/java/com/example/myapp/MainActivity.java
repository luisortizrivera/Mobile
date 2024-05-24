package com.example.myapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText firstNum;
    private EditText secondNum;
    private TextView resultView;

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
        firstNum = (EditText) findViewById(R.id.firstText);
        secondNum = (EditText) findViewById(R.id.SecondNum);
        resultView = (TextView) findViewById(R.id.resultTV);

        Button addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstNumStr = firstNum.getText().toString();
                String secondNumStr = secondNum.getText().toString();

                int num1 = Integer.parseInt(firstNumStr);
                int num2 = Integer.parseInt(secondNumStr);
                int resultValue = num1 + num2;
                resultView.setText(String.valueOf(resultValue));
            }
        });
    }
}