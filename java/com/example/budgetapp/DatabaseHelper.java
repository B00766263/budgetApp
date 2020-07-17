package com.example.budgetapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Database version
    public static final int DB_VERSION = 1;

    //database name
    public static final String DB_NAME = "Budget.App";

    //Table names
    public static final String TABLE_SPENDING = "spending";
    public static final String TABLE_WASTAGE = "wastage";

    //Column names for Spending and Wastage Table
    public static final String KEY_ID = "id";
    public static final String KEY_CATEGORY = "category";
    public static final String KEY_AMOUNT = "spentMoney";
    public static final String KEY_WASTEDMONEY = "wastedMoney";
    //public static final String KEY_DATE = "date";

    //database creation, constructor
    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //creating the tables in SQLite
    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlTableSpending = "CREATE TABLE " + TABLE_SPENDING + "(" + KEY_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_CATEGORY
                + " TEXT," + KEY_AMOUNT + " INTEGER, collected_date DEFAULT CURRENT_DATE)";

        String sqlTableWastage = "CREATE TABLE wastage (id, wastedMoney INTEGER, collected_date DEFAULT CURRENT_DATE)";

        db.execSQL(sqlTableSpending);
        db.execSQL(sqlTableWastage);
    }

    //check if the table is empty or not and make another updated table
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       /*if (db == null)
          db = this.getWritableDatabase();
          db.execSQL("drop table if exists " + TABLE_SPENDING);
        db.execSQL("drop table if exists " + TABLE_WASTAGE);
        //create new tables
        */
        onCreate(db);
    }

    //inserting the data
    public boolean insertSpending(String amount, String categ){

        SQLiteDatabase db = this.getWritableDatabase();

        //inserting spent money
        ContentValues spendingValues = new ContentValues();
        spendingValues.put(KEY_AMOUNT, amount);
        spendingValues.put("category", categ);

        long resultSpending = db.insertWithOnConflict(TABLE_SPENDING, null, spendingValues, SQLiteDatabase.CONFLICT_IGNORE);

        if (resultSpending == -1)
           return false;
        else
            return true;
    }

    public boolean insertWastage(String amount){

        SQLiteDatabase db = this.getWritableDatabase();

        //inserting wasted money
        ContentValues wastageValues = new ContentValues();
        wastageValues.put(KEY_WASTEDMONEY, amount);
        long resultWastage = db.insert(TABLE_WASTAGE, null, wastageValues);

        if (resultWastage == -1)
            return false;
        else
            return true;
    }

    public Cursor getTodaysTotalSpending() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor todaySpending = db.rawQuery("SELECT SUM( spentMoney ) as Total FROM spending WHERE collected_date = CURRENT_DATE ", null);
        return todaySpending;
    }

    public Cursor getTodaysWastage() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor todayWastage = db.rawQuery("SELECT SUM(wastedMoney) as Total FROM wastage WHERE collected_date = CURRENT_DATE", null);
        return todayWastage;
    }

    public Cursor getTodaysSpendingCategory(String spendCategToday){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor todaysSpendCateg = db.rawQuery("SELECT SUM(spentMoney) FROM spending WHERE category = '" + spendCategToday + "' AND collected_date = CURRENT_DATE;", null);
        return todaysSpendCateg;
    }

    public Cursor getCurrentWeekSpendingCategory(String spendCateg) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor weekSpendCateg = db.rawQuery("SELECT SUM(spentMoney) FROM spending WHERE category = \"" + spendCateg + "\" AND collected_date BETWEEN datetime('now', '-6 days') AND datetime('now')", null);
        return weekSpendCateg;
    }

    public Cursor getCurrentWeeksTotalSpending() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor currentWeekSpending = db.rawQuery("SELECT SUM( spentMoney ) as Total FROM spending WHERE collected_date BETWEEN datetime('now', '-6 days') AND datetime('now')", null);
        return currentWeekSpending;
    }

    public Cursor getCurrentWeekWastage() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor currentWeekWastage = db.rawQuery("SELECT SUM( wastedMoney ) as Total FROM wastage WHERE collected_date BETWEEN datetime('now', '-6 days') AND datetime('now')", null);
        return currentWeekWastage;
    }

    public Cursor getLastWeeksTotalSpending() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor lastWeekSpending = db.rawQuery("SELECT SUM( spentMoney ) as Total FROM spending WHERE collected_date BETWEEN datetime('now', '-13 days') AND datetime('now', '-6 days')", null);
        return lastWeekSpending;
    }

    public Cursor getLastWeeksTotalWastage() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor lastWeekWastage = db.rawQuery("SELECT SUM( wastedMoney ) as Total FROM wastage WHERE collected_date BETWEEN datetime('now', '-13 days') AND datetime('now', '-6 days')", null);
        return lastWeekWastage;
    }

    public Cursor getCurrentMonthTotalSpending() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor currentMonthSpending = db.rawQuery("SELECT SUM( spentMoney ) as Total FROM spending WHERE collected_date BETWEEN datetime('now','localtime','start of month') AND datetime('now','localtime')", null);
        return currentMonthSpending;
    }


    public Cursor getCurrentMonthWastage() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor currentMonthWastage = db.rawQuery("SELECT SUM( wastedMoney ) as Total FROM wastage WHERE collected_date BETWEEN date('now','localtime','start of month') AND date('now','localtime')", null);
        return currentMonthWastage;
    }
    public Cursor getLastMonthTotalSpending() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor lastMonthSpending = db.rawQuery("SELECT SUM( spentMoney ) as Total FROM spending WHERE collected_date >= date('now', 'start of month', '-1 month')  AND collected_date < date('now', 'start of month')", null);
        return lastMonthSpending;
    }

    public Cursor getLastMonthWastage() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor lastMonthWastage = db.rawQuery("SELECT SUM( wastedMoney ) as Total FROM wastage WHERE collected_date >= date('now', 'start of month', '-1 month')  AND collected_date < date('now', 'start of month')", null);
        return lastMonthWastage;
    }

    /*
    //adding Categories to the table
    public boolean addCategory(String name) {

        //use SQLiteDatabase instance in insert method
        SQLiteDatabase db = this.getReadableDatabase();

        //instance of class ContentValue
        ContentValues contentValues = new ContentValues();

        //Values to be inserted
        contentValues.put(KEY_CATEGORY, name);

        long inserted = db.insert(TABLE_SPENDING, null, contentValues);

        return inserted == -1 ? false : true;
    }

    public void updateAmounts(String id, String category,String spentAmount, String wastedAmount){

        SQLiteDatabase db = this.getWritableDatabase();

        //updating spent amount in spending table
        ContentValues spentValues = new ContentValues();
       // spentValues.put(KEY_CATEGORY, category);
        spentValues.put(KEY_AMOUNT, spentAmount);
        db.update(TABLE_SPENDING, spentValues, KEY_ID + " = ?", new String[] {String.valueOf(id)});

        //updating wasted amount in wastage table
        ContentValues wastedValues = new ContentValues();
        wastedValues.put(KEY_WASTEDMONEY, wastedAmount);
        db.update(TABLE_WASTAGE, wastedValues, KEY_ID + " = ?", new String[] {String.valueOf(id)});
      }

    public Cursor getTodaysSpendingCategory(String spendCategToday){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor todaysSpendCateg = db.rawQuery("SELECT SUM(spentMoney) as Total FROM spending WHERE collected_date = CURRENT_DATE","and GROUP BY category = \"" + spendCategToday);
        return todaysSpendCateg;
    }

    public Cursor getCurrentWeekSpendingCategory(String spendCateg) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor weekSpendCateg = db.rawQuery("SELECT SUM(spentMoney) FROM spending GROUP BY category = \"" + spendCateg + "\" AND collected_date > CURRENT_DATE - 8;", null);
        return weekSpendCateg;
    }

    public Cursor getCurrentWeekSpendingCategoryFood() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor weekSpendCateg = db.rawQuery("SELECT SUM(spentMoney) as Total FROM spending GROUP BY category = \"FOOD\" AND collected_date > CURRENT_DATE - 8;", null);
        return weekSpendCateg;
    }
     */
}
