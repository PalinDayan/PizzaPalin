package com.example.palin_dayan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton startBtn= findViewById(R.id.start_btn);
        EditText name = findViewById(R.id.input_name);
        EditText address = findViewById(R.id.input_address);
        EditText city = findViewById(R.id.input_city);
        TextView nameText= findViewById(R.id.textName);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().matches("")  ||city.getText().toString().matches("") || address.getText().toString().matches("")) {
                    Toast.makeText(MainActivity.this, R.string.output, Toast.LENGTH_SHORT).show();
                    if (name.getText().toString().matches(""))
                        name.setHintTextColor(getResources().getColor(R.color.red));

                    if (city.getText().toString().matches(""))
                        city.setHintTextColor(getResources().getColor(R.color.red));

                    if (address.getText().toString().matches(""))
                        address.setHintTextColor(getResources().getColor(R.color.red));
                }
                else
                {
                    String useName = name.getText().toString();
                    String addressText= address.getText().toString();
                    String cityText=city.getText().toString();
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("user_name", useName);
                    intent.putExtra("addressText", addressText);
                    intent.putExtra("cityText", cityText);
                    startActivity(intent);
                }
            }
        });

    }
}