package com.example.palin_dayan;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import org.w3c.dom.Text;

public class SecondActivity extends Activity {

    private RadioGroup radioGroup;
    ImageView pizza;
    CheckBox checkBox1;
    CheckBox checkBox2;
    CheckBox checkBox3;
    CheckBox checkBox4 ;
    CheckBox checkBox5;
    CheckBox checkBox6 ;
    CheckBox checkBox7 ;
    CheckBox addTop;
    int total=0;
    int sizeNum;
    HorizontalScrollView hori;
    ImageView toping1;
    ImageView toping2;
    ImageView toping3;
    ImageView toping4;
    ImageView toping5;
    ImageView toping6;
    ImageView toping7;
    int numBefor=0;
    String adressText;
    String cityText;
    int numToping=0;
    String numOfBtnStr ="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        String useName = getIntent().getStringExtra("user_name");
        TextView textView = findViewById(R.id.username_output);
        textView.setText(" " + useName);
        adressText = getIntent().getStringExtra("addressText");
        cityText = getIntent().getStringExtra("cityText");



        radioGroup = (RadioGroup)findViewById(R.id.groupradio);
        pizza= findViewById(R.id.pizza);
        radioGroup.clearCheck();
        RadioButton rad1= findViewById(R.id.radia_id1);
        RadioButton rad2= findViewById(R.id.radia_id2);
        RadioButton rad3= findViewById(R.id.radia_id3);


        TableRow tableR= findViewById(R.id.tableR);
        hori= findViewById(R.id.horizons);
        addTop= findViewById(R.id.addTop);
        checkBox1 = findViewById(R.id.checkbox1);
        checkBox2 = findViewById(R.id.checkbox2);
        checkBox3 = findViewById(R.id.checkbox3);
        checkBox4 = findViewById(R.id.checkbox4);
        checkBox5 = findViewById(R.id.checkbox5);
        checkBox6 = findViewById(R.id.checkbox6);
        checkBox7 = findViewById(R.id.checkbox7);

        CheckBoxListener checkBoxListener= new CheckBoxListener();
        checkBox1.setOnClickListener(checkBoxListener);
        checkBox2.setOnClickListener(checkBoxListener);
        checkBox3.setOnClickListener(checkBoxListener);
        checkBox4.setOnClickListener(checkBoxListener);
        checkBox5.setOnClickListener(checkBoxListener);
        checkBox6.setOnClickListener(checkBoxListener);
        checkBox7.setOnClickListener(checkBoxListener);



        LinearLayout linbuttonEnd = findViewById(R.id.buttonEnd);
        CheckBox addDrink= findViewById(R.id.addDrink);
        RelativeLayout relDrink= findViewById(R.id.drinkText_layout);
        EditText input_drink= findViewById(R.id.input_drink);
        Button choseDrink= findViewById(R.id.choseDrink);
        LinearLayout root_layout;
        root_layout=findViewById(R.id.drinkSpineer);
        Button reset= findViewById(R.id.Reset);
        Button finishBtn = findViewById(R.id.finishOrder);

        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String totalText= String.valueOf(total);
                String numOfTopText = String.valueOf(numToping);
                if(total==0)
                {
                    Toast.makeText(SecondActivity.this, R.string.wrongFinish, Toast.LENGTH_SHORT).show();
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SecondActivity.this);
                    builder.setMessage(R.string.alertTextFinish)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(SecondActivity.this, FinishActivity.class);
                                    intent.putExtra("addressText", adressText);
                                    intent.putExtra("cityText", cityText);
                                    intent.putExtra("total", totalText);
                                    intent.putExtra("numToping", numOfTopText);
                                    intent.putExtra("numBottel", numOfBtnStr);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .setCancelable(false)
                            .show();
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(SecondActivity.this);
                builder.setMessage(R.string.alertTextReset)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                rad1.setChecked(false);
                                rad2.setChecked(false);
                                rad3.setChecked(false);
                                pizza.getLayoutParams().width = 0;
                                pizza.getLayoutParams().height = 0;
                                pizza.requestLayout();
                                cancelTop();
                                addTop.setVisibility(View.GONE);
                                relDrink.setVisibility(View.GONE);
                                input_drink.setText("");
                                root_layout.removeAllViews();
                                hori.setVisibility(View.GONE);
                                addDrink.setVisibility(View.GONE);
                                addDrink.setChecked(false);
                                input_drink.setHintTextColor(getResources().getColor(R.color.textColor));
                                total=0;
                                numBefor=0;
                                numOfBtnStr ="0";
                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setCancelable(false).show();
            }
        });

        choseDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] strings={(String) getResources().getText(R.string.cola),(String) getResources().getText(R.string.colaZiro),(String) getResources().getText(R.string.sprite),(String) getResources().getText(R.string.spraiteZero),(String) getResources().getText(R.string.panta),(String) getResources().getText(R.string.fuzeTea),(String) getResources().getText(R.string.water),(String) getResources().getText(R.string.orange),(String) getResources().getText(R.string.grape)};

                numOfBtnStr= input_drink.getText().toString();
                total=total-(numBefor*10);
                root_layout.removeAllViews();
                if(numOfBtnStr.matches("[0-9]+")) {
                    int numOfBtns = Integer.parseInt(numOfBtnStr);
                    numBefor= numOfBtns;

                    for (int i = 0; i < numOfBtns; i++) {

                        Spinner spinner = new Spinner(SecondActivity.this);
                        spinner.setAdapter(new ArrayAdapter<>(SecondActivity.this, android.R.layout.simple_spinner_dropdown_item, strings));
                        TextView textDrink = new TextView(SecondActivity.this);
                        textDrink.setText(R.string.drinkText);

                        root_layout.addView(textDrink);
                        root_layout.addView(spinner);

                    }
                    total = total+(numOfBtns*10);
                    Toast.makeText(SecondActivity.this, total+"", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(SecondActivity.this, R.string.WrongDrink, Toast.LENGTH_SHORT).show();
                    input_drink.setHintTextColor(getResources().getColor(R.color.red));
                }
            }
        });

        addDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(addDrink.isChecked()) {
                    relDrink.setVisibility(View.VISIBLE);
                }
                else {
                    relDrink.setVisibility(View.GONE);
                    input_drink.setText("");
                    root_layout.removeAllViews();
                    total=total-(numBefor*10);
                    input_drink.setHintTextColor(getResources().getColor(R.color.textColor));
                    numBefor=0;
                    numOfBtnStr ="0";
                }
            }
        });




        addTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(addTop.isChecked())
                    hori.setVisibility(View.VISIBLE);
                else {
                    hori.setVisibility(View.GONE);
                    cancelTop();
                }
                Toast.makeText(SecondActivity.this, total+"", Toast.LENGTH_SHORT).show();
            }
        });


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
                String size = radioButton.getText().toString();
                addTop.setVisibility(View.VISIBLE);
                linbuttonEnd.setVisibility(View.VISIBLE);
                addDrink.setVisibility(View.VISIBLE);
                cancelTop();
                switch (size) {
                    case "small 20 NIS": {
                        sizeNum = 2;
                        total=20;
                        break;
                    }
                    case "medium 35 NIS": {
                        sizeNum= 3;
                        total=35;
                        break;
                    }
                    case "large 50 NIS": {
                        sizeNum= 4;
                        total=50;
                        break;
                    }
                    case "קטן 20 שח": {
                        sizeNum= 2;
                        total=20;
                        break;
                    }
                    case "בינוני 35 שח": {
                        sizeNum= 3;
                        total=35;
                        break;
                    }
                    case "גדול 50 שח": {
                        sizeNum =4;
                        total=50;
                        break;
                    }
                }
                pizza.getLayoutParams().width = 100*sizeNum;
                pizza.getLayoutParams().height = 100*sizeNum;
                pizza.requestLayout();
                total = total+numBefor*10;

            }
        });


    }

    private class CheckBoxListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            toping1= findViewById(R.id.toping1);
            toping2= findViewById(R.id.toping2);
            toping3= findViewById(R.id.toping3);
            toping4= findViewById(R.id.toping4);
            toping5= findViewById(R.id.toping5);
            toping6= findViewById(R.id.toping6);
            toping7= findViewById(R.id.toping7);
            switch(v.getId()) {
                case R.id.checkbox1: {
                    if (checkBox1.isChecked())
                    {
                        toping1.getLayoutParams().width = 100*sizeNum-30;
                        toping1.getLayoutParams().height = 100*sizeNum-30;
                        toping1.requestLayout();
                        total+=5;
                        numToping++;
                    }
                    else
                    {
                        toping1.getLayoutParams().width = 0;
                        toping1.getLayoutParams().height = 0;
                        toping1.requestLayout();
                        total= total-5;
                        numToping--;
                    }
                    break;
                }
                case R.id.checkbox2:
                {
                    if (checkBox2.isChecked())
                    {
                        toping2.getLayoutParams().width = 100*sizeNum-30;
                        toping2.getLayoutParams().height = 100*sizeNum-30;
                        toping2.requestLayout();
                        total+=5;
                        numToping++;
                    }
                    else
                    {
                        toping2.getLayoutParams().width = 0;
                        toping2.getLayoutParams().height = 0;
                        toping2.requestLayout();
                        total-=5;
                        numToping--;
                    }
                    break;
                }
                case R.id.checkbox3:
                {
                    if (checkBox3.isChecked())
                    {
                        toping3.getLayoutParams().width = 100*sizeNum-30;
                        toping3.getLayoutParams().height = 100*sizeNum-30;
                        toping3.requestLayout();
                        total+=5;
                        numToping++;
                    }
                    else
                    {
                        toping3.getLayoutParams().width = 0;
                        toping3.getLayoutParams().height = 0;
                        toping3.requestLayout();
                        total-=5;
                        numToping--;
                    }
                    break;
                }
                case R.id.checkbox4:
                {
                    if (checkBox4.isChecked())
                    {
                        toping4.getLayoutParams().width = 100*sizeNum-30;
                        toping4.getLayoutParams().height = 100*sizeNum-30;
                        toping4.requestLayout();
                        total+=5;
                        numToping++;
                    }
                    else
                    {
                        toping4.getLayoutParams().width = 0;
                        toping4.getLayoutParams().height = 0;
                        toping4.requestLayout();
                        total-=5;
                        numToping--;
                    }
                    break;
                }
                case R.id.checkbox5:
                {
                    if (checkBox5.isChecked())
                    {
                        toping5.getLayoutParams().width = 100*sizeNum-30;
                        toping5.getLayoutParams().height = 100*sizeNum-30;
                        toping5.requestLayout();
                        total+=5;
                        numToping++;
                    }
                    else
                    {
                        toping5.getLayoutParams().width = 0;
                        toping5.getLayoutParams().height = 0;
                        toping5.requestLayout();
                        total-=5;
                        numToping--;
                    }
                    break;

                }
                case R.id.checkbox6:
                {
                    if (checkBox6.isChecked())
                    {
                        toping6.getLayoutParams().width = 100*sizeNum-30;
                        toping6.getLayoutParams().height = 100*sizeNum-30;
                        toping6.requestLayout();
                        total+=5;
                        numToping++;
                    }
                    else
                    {
                        toping6.getLayoutParams().width = 0;
                        toping6.getLayoutParams().height = 0;
                        toping6.requestLayout();
                        total-=5;
                        numToping--;
                    }
                    break;
                }
                case R.id.checkbox7:
                {
                    if (checkBox7.isChecked())
                    {
                        toping7.getLayoutParams().width = 100*sizeNum-30;
                        toping7.getLayoutParams().height = 100*sizeNum-30;
                        toping7.requestLayout();
                        total+=5;
                        numToping++;
                    }
                    else
                    {
                        toping7.getLayoutParams().width = 0;
                        toping7.getLayoutParams().height = 0;
                        toping7.requestLayout();
                        total-=5;
                        numToping--;
                    }
                    break;
                }
            }
            Toast.makeText(SecondActivity.this, total+"", Toast.LENGTH_SHORT).show();
        }

    }
    private void cancelTop()
    {
        numToping=0;
        hori.setVisibility(View.GONE);
        if(checkBox1.isChecked())
        {
            checkBox1.setChecked(false);
            toping1.getLayoutParams().width = 0;
            toping1.getLayoutParams().height = 0;
            toping1.requestLayout();
            total-=5;
        }
        if(checkBox2.isChecked())
        {
            checkBox2.setChecked(false);
            toping2.getLayoutParams().width = 0;
            toping2.getLayoutParams().height = 0;
            toping2.requestLayout();
            total-=5;
        }
        if(checkBox3.isChecked()) {
            checkBox3.setChecked(false);
            toping3.getLayoutParams().width = 0;
            toping3.getLayoutParams().height = 0;
            toping3.requestLayout();
            total-=5;
        }
        if(checkBox4.isChecked()){
            checkBox4.setChecked(false);
            toping4.getLayoutParams().width = 0;
            toping4.getLayoutParams().height = 0;
            toping4.requestLayout();
            total-=5;
        }

        if(checkBox5.isChecked()) {
            checkBox5.setChecked(false);
            toping5.getLayoutParams().width = 0;
            toping5.getLayoutParams().height = 0;
            toping5.requestLayout();
            total-=5;
        }
        if(checkBox6.isChecked()){
            checkBox6.setChecked(false);
            toping6.getLayoutParams().width = 0;
            toping6.getLayoutParams().height = 0;
            toping6.requestLayout();
            total-=5;
        }
        if(checkBox7.isChecked()){
            checkBox7.setChecked(false);
            toping7.getLayoutParams().width = 0;
            toping7.getLayoutParams().height = 0;
            toping7.requestLayout();
            total-=5;
        }
        if(addTop.isChecked())
            addTop.setChecked(false);
    }


}
