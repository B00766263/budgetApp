package com.example.budgetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LastWeek extends AppCompatActivity implements View.OnClickListener{

    DatabaseHelper dbHelper;
    TextView moneySpentLW, moneyWastedLW, moneyWellSpentLW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_week);

        dbHelper = new DatabaseHelper(this);
        //dbHelper.onUpgrade(null, 1, 1);

        Button back = (Button) findViewById(R.id.backBtn);
        back.setOnClickListener(this);

        Button home = (Button) findViewById(R.id.homeBtn);
        home.setOnClickListener(this);

        //last week's spending
        moneySpentLW = (TextView) findViewById(R.id.moneySpentLW);
        Cursor cursorLastWeekSpending = dbHelper.getLastWeeksTotalSpending();
        StringBuilder stringBuilderSpendingLW = new StringBuilder();

        while (cursorLastWeekSpending.moveToNext()){

            stringBuilderSpendingLW.append(cursorLastWeekSpending.getDouble(0));
        }

        double outputSpentLW = Double.parseDouble(stringBuilderSpendingLW.toString());
        outputSpentLW = Math.round(outputSpentLW *100)/100.0;

        moneySpentLW.setText(String.valueOf(outputSpentLW));

        //last week's wastage
        moneyWastedLW = (TextView) findViewById(R.id.moneyWastedLW);
        Cursor cursorWastageLW = dbHelper.getLastWeeksTotalWastage();
        StringBuilder stringBuilderWastageLW = new StringBuilder();

        while (cursorWastageLW.moveToNext()){

            stringBuilderWastageLW.append(cursorWastageLW.getDouble(0));
        }

        double outputWastedLW = Double.parseDouble(stringBuilderWastageLW.toString());
        outputWastedLW = Math.round(outputWastedLW *100)/100.0;

        moneyWastedLW.setText(String.valueOf(outputWastedLW));
        //String wastedLW = stringBuilderWastageLW.toString();
        //moneyWastedLW.setText(wastedLW);

        //last week's well spent money
        moneyWellSpentLW = (TextView) findViewById(R.id.moneyWellSpentLW);
        //double wellSpentLW = tryParse(outputSpentLW) - tryParse(outputWastedLW);
        double wellSpentLW = outputSpentLW - outputWastedLW;
        wellSpentLW = Math.round(wellSpentLW *100)/100.0;
        moneyWellSpentLW.setText(wellSpentLW + "");

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
