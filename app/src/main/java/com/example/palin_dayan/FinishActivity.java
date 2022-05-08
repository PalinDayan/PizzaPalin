package com.example.palin_dayan;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class FinishActivity extends Activity {

    String adressText;
    String cityText;
    String total;
    String numToping;
    String numOfBtnStr;
    String plusSpace;



    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finish_activity);

        total = getIntent().getStringExtra("total");
        TextView totalView= findViewById(R.id.inputCost);
        totalView.setText(" "+ total);
        TextView nis= findViewById(R.id.nis);
        plusSpace= nis.getText().toString();
        nis.setTextSize(20);
        nis.setText(" "+ plusSpace);
        cityText = getIntent().getStringExtra("cityText");
        TextView cityView= findViewById(R.id.input_city);
        cityView.setText(" "+ cityText);
        adressText = getIntent().getStringExtra("addressText");
        TextView adressView= findViewById(R.id.input_address);
        adressView.setText(" "+ adressText);

        numToping = getIntent().getStringExtra("numToping");
        TextView topingView= findViewById(R.id.inputeoping);
        if(Integer.parseInt(numToping) == 0)
        {
            TextView withOut= findViewById(R.id.withOut);
            plusSpace= withOut.getText().toString();
            withOut.setTextSize(20);
            withOut.setText(" "+plusSpace);

        }
        else {
            TextView with= findViewById(R.id.withTop);
            plusSpace= with.getText().toString();
            with.setTextSize(20);
            with.setText(" "+plusSpace);
            topingView.setText(" "+numToping+" ");
            TextView toping= findViewById(R.id.toping);
            toping.setTextSize(20);
        }
        numOfBtnStr = getIntent().getStringExtra("numBottel");
        TextView buttelView= findViewById(R.id.inputBottels);
        if(Integer.parseInt(numOfBtnStr) == 0)
        {
            TextView withOutBot= findViewById(R.id.withOutBott);
            plusSpace= withOutBot.getText().toString();
            withOutBot.setTextSize(20);
        }
        else
        {
            TextView buyBottel= findViewById(R.id.buyBottel);
            plusSpace= buyBottel.getText().toString();
            buyBottel.setTextSize(20);
            buttelView.setText(" " + numOfBtnStr+ " ");
            TextView bottel= findViewById(R.id.bottel);
            bottel.setTextSize(20);
        }


    }
}
