package com.example.budgetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CurrentWeek extends AppCompatActivity implements View.OnClickListener{

    DatabaseHelper dbHelper;
    TextView moneySpentCW, moneyWastedCW, moneyWellSpentCW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_week);

        dbHelper = new DatabaseHelper(this);

        Button back = (Button) findViewById(R.id.backBtn);
        back.setOnClickListener(this);

        Button home = (Button) findViewById(R.id.homeBtn);
        home.setOnClickListener(this);

        //current week's spending
        moneySpentCW = (TextView) findViewById(R.id.moneySpentCW);
        Cursor cursorWeekSpending = dbHelper.getCurrentWeeksTotalSpending();
        StringBuilder stringBuilderTotalWeek = new StringBuilder();

        while (cursorWeekSpending.moveToNext()){

            stringBuilderTotalWeek.append(cursorWeekSpending.getDouble(0));
        }

        double outputSpentCW = Double.parseDouble(stringBuilderTotalWeek.toString());
        outputSpentCW = Math.round(outputSpentCW *100)/100.0;

        moneySpentCW.setText(String.valueOf(outputSpentCW));
        //String spentCW = stringBuilderTotalWeek.toString();
        //moneySpentCW.setText(spentCW);

        //current week's wastage
        moneyWastedCW = (TextView) findViewById(R.id.moneyWastedCW);
        Cursor cursorWastageCW = dbHelper.getCurrentWeekWastage();
        StringBuilder stringBuilderWastageCW = new StringBuilder();

        while (cursorWastageCW.moveToNext()){

            stringBuilderWastageCW.append(cursorWastageCW.getDouble(0));
        }

        double outputWastedCW = Double.parseDouble(stringBuilderWastageCW.toString());
        outputWastedCW = Math.round(outputWastedCW *100)/100.0;

        moneyWastedCW.setText(String.valueOf(outputWastedCW));
        //String wastedCW = stringBuilderWastageCW.toString();
        //moneyWastedCW.setText(wastedCW);

        //current week's well spent money
        moneyWellSpentCW = (TextView) findViewById(R.id.moneyWellSpentCW);
        double wellSpentCW = outputSpentCW - outputWastedCW;
        wellSpentCW = Math.round(wellSpentCW *100)/100.0;
        //double wellSpentCW = tryParse(spentCW) - tryParse(wastedCW);
        moneyWellSpentCW.setText(wellSpentCW + "");

    }

    @Override
    public void onClick(View v) {

        Intent intent;

        switch (v.getId()) {

            case R.id.backBtn:
                onBackPressed();
                break;

            case R.id.homeBtn:
                intent = new Intent(this, MainActivity2.class);
                startActivity(intent);
                break;
        }
    }
    private double tryParse(String s){

        try {
            return Double.parseDouble(s);
        }catch (NumberFormatException e){
            return 0;
        }
    }
}
