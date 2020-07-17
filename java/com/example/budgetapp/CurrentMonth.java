package com.example.budgetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CurrentMonth extends AppCompatActivity implements View.OnClickListener{

    DatabaseHelper dbHelper;
    TextView moneySpentCM, moneyWastedCM, moneyWellSpentCM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_month);

        dbHelper = new DatabaseHelper(this);

        Button back = (Button) findViewById(R.id.backBtn);
        back.setOnClickListener(this);

        Button home = (Button) findViewById(R.id.homeBtn);
        home.setOnClickListener(this);

        //current month's spending
        moneySpentCM = (TextView) findViewById(R.id.moneySpentCM);
        Cursor cursorCurMonthSpending = dbHelper.getCurrentMonthTotalSpending();
        StringBuilder stringBuilderTotalMonth = new StringBuilder();

        while (cursorCurMonthSpending.moveToNext()){

            stringBuilderTotalMonth.append(cursorCurMonthSpending.getDouble(0));
        }

        double outputSpentCM = Double.parseDouble(stringBuilderTotalMonth.toString());
        outputSpentCM = Math.round(outputSpentCM *100)/100.0;

        moneySpentCM.setText(String.valueOf(outputSpentCM));
        //String spentCM = stringBuilderTotalMonth.toString();
        //moneySpentCM.setText(spentCM);

        //current month's wastage
        moneyWastedCM = (TextView) findViewById(R.id.moneyWastedCM);
        Cursor cursorWastageCM = dbHelper.getCurrentMonthWastage();
        StringBuilder stringBuilderWastageCM = new StringBuilder();

        while (cursorWastageCM.moveToNext()){

            stringBuilderWastageCM.append(cursorWastageCM.getDouble(0));
        }

        double outputWastedCM = Double.parseDouble(stringBuilderWastageCM.toString());
        outputWastedCM = Math.round(outputWastedCM *100)/100.0;

        moneyWastedCM.setText(String.valueOf(outputWastedCM));
        //String wastedCM = stringBuilderWastageCM.toString();
        //moneyWastedCM.setText(wastedCM);

        //current month's well spent money
        moneyWellSpentCM = (TextView) findViewById(R.id.moneyWellSpentCM);
        double wellSpentCM = outputSpentCM - outputWastedCM;
        wellSpentCM = Math.round(wellSpentCM *100)/100.0;
        //double wellSpentCM = tryParse(spentCM) - tryParse(wastedCM);
        moneyWellSpentCM.setText(wellSpentCM + "");
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
