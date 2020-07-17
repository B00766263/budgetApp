package com.example.budgetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LastMonth extends AppCompatActivity implements View.OnClickListener{

    DatabaseHelper dbHelper;
    TextView moneySpentLM, moneyWastedLM, moneyWellSpentLM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_month);

        dbHelper = new DatabaseHelper(this);

        Button back = (Button) findViewById(R.id.backBtn);
        back.setOnClickListener(this);

        Button home = (Button) findViewById(R.id.homeBtn);
        home.setOnClickListener(this);

        //last month's spending
        moneySpentLM = (TextView) findViewById(R.id.moneySpentLM);
        Cursor cursorLastMonthSpending = dbHelper.getLastMonthTotalSpending();
        StringBuilder stringBuilderSpendingLM = new StringBuilder();

        while (cursorLastMonthSpending.moveToNext()){

            stringBuilderSpendingLM.append(cursorLastMonthSpending.getDouble(0));
        }

        double outputSpentLM = Double.parseDouble(stringBuilderSpendingLM.toString());
        outputSpentLM = Math.round(outputSpentLM *100)/100.0;

        moneySpentLM.setText(String.valueOf(outputSpentLM));
        //String spentLM = stringBuilderSpendingLM.toString();
        //moneySpentLM.setText(spentLM);

        //last month's wastage
        moneyWastedLM = (TextView) findViewById(R.id.moneyWastedLM);
        Cursor cursorWastageLM = dbHelper.getLastMonthWastage();
        StringBuilder stringBuilderWastageLM = new StringBuilder();

        while (cursorWastageLM.moveToNext()){

            stringBuilderWastageLM.append(cursorWastageLM.getDouble(0));
        }

        double outputWastedLM = Double.parseDouble(stringBuilderWastageLM.toString());
        outputWastedLM = Math.round(outputWastedLM *100)/100.0;

        moneyWastedLM.setText(String.valueOf(outputWastedLM));
        //String wastedLM = stringBuilderWastageLM.toString();
        //moneyWastedLM.setText(wastedLM);

        //last month's well spent money
        moneyWellSpentLM = (TextView) findViewById(R.id.moneyWellSpentLM);
        double wellSpentLM = outputSpentLM - outputWastedLM;
        wellSpentLM = Math.round(wellSpentLM *100)/100.0;
        //double wellSpentLM = tryParse(spentLM) - tryParse(wastedLM);
        moneyWellSpentLM.setText(wellSpentLM + "");
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
