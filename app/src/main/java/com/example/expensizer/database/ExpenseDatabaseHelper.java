package com.example.expensizer.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.expensizer.model.ExpenseItem;

import static com.example.expensizer.Constacts.TAG;

public class ExpenseDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "ExpenseDB";
    private static final int VERSION = 1;
    private static final String EXPENSE_TABLE_NAME = "ExpenseTable";

    private static final String COL_ID = "id";
    private static final String COL_DESC = "description";
    private static final String COL_PRICE = "price";
    private static final String COL_NOTES = "notes";
    private static final String COL_DATE = "date";
    private static final String COL_CATEGORY = "catogory";


    private static ExpenseDatabaseHelper instance;

    private ExpenseDatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    public ExpenseDatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new ExpenseDatabaseHelper(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "create table " + EXPENSE_TABLE_NAME + "(" +
                COL_ID + " integer primary key autoincrement," +
                COL_DESC + " text not null," +
                COL_PRICE + " real not null," +
                COL_CATEGORY + " text," +
                COL_DATE + " numeric," +
                COL_NOTES + " text )";
        try {
            sqLiteDatabase.execSQL(query);
        } catch (SQLException ex) {
            Log.d(TAG, "ExpenseDatabaseHelper onCreate: " + ex.toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public boolean addExpense(ExpenseItem expenseItem) {
        ContentValues values = new ContentValues();
        values.put(COL_DESC, expenseItem.getDescription());
        values.put(COL_PRICE, expenseItem.getPrice());
        values.put(COL_CATEGORY, expenseItem.getCategory());
        values.put(COL_DATE, expenseItem.getTime());
        values.put(COL_NOTES, expenseItem.getNote());

        try {
            SQLiteDatabase db = getWritableDatabase();
            db.insert(EXPENSE_TABLE_NAME, null, values);
            Log.e(TAG, "addExpense added expenses successfully");
            return true;
        } catch (SQLException ex) {
            Log.e(TAG, "addExpense " + ex.toString());
            return false;
        }
    }
}
