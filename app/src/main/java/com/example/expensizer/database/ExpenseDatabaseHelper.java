package com.example.expensizer.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.expensizer.model.ExpenseCategory;
import com.example.expensizer.model.ExpenseItem;

import java.util.ArrayList;

import static com.example.expensizer.Constacts.TAG;

public class ExpenseDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "ExpenseDB";
    private static final int VERSION = 1;
    private static final String EXPENSE_TABLE_NAME = "ExpenseTable1";
    private static final String CATEGORY_TABLE_NAME = "ExpenseCategory";

    private static final String COL_ID = "id";
    private static final String COL_DESC = "description";
    private static final String COL_PRICE = "price";
    private static final String COL_NOTES = "notes";
    private static final String COL_DATE = "date";
    private static final String COL_CATEGORY = "catogory";

    private static final String COL_CATEGORY_ID = "ide";
    private static final String COL_CATEGORIES = "categories";

    private static ExpenseDatabaseHelper instance;

    private ExpenseDatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    public static ExpenseDatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new ExpenseDatabaseHelper(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createTable(sqLiteDatabase);
        createCategoryTable(sqLiteDatabase);
        addDefaultCategories(sqLiteDatabase);
    }

    private void createTable(SQLiteDatabase sqLiteDatabase) {
        String query = "create table " + EXPENSE_TABLE_NAME + "(" +
                COL_ID + " integer primary key autoincrement," +
                COL_DESC + " text not null," +
                COL_PRICE + " real not null," +
                COL_CATEGORY + " text," +
                COL_DATE + " text," +
                COL_NOTES + " text )";
        try {
            sqLiteDatabase.execSQL(query);
        } catch (SQLException ex) {
            Log.d(TAG, "ExpenseDatabaseHelper onCreate: " + ex.toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        createTable(sqLiteDatabase);
        createCategoryTable(sqLiteDatabase);
    }

    private void createCategoryTable(SQLiteDatabase sqLiteDatabase) {
        String query = "create table " + CATEGORY_TABLE_NAME + "(" +
                COL_CATEGORY_ID + " integer primary key autoincrement," +
                COL_CATEGORIES + " text )";
        try {
            sqLiteDatabase.execSQL(query);
        } catch (SQLException ex) {
            Log.d(TAG, "ExpenseDatabaseHelper onCreate: " + ex.toString());
        }
    }

    private void addDefaultCategories(SQLiteDatabase sqLiteDatabase) {
        addCategory(new ExpenseCategory("Grocery"), sqLiteDatabase);
        addCategory(new ExpenseCategory("Kitchen"), sqLiteDatabase);
        addCategory(new ExpenseCategory("Furniture"), sqLiteDatabase);
        addCategory(new ExpenseCategory("Rent"), sqLiteDatabase);
        addCategory(new ExpenseCategory("Services"), sqLiteDatabase);
        addCategory(new ExpenseCategory("Cloths"), sqLiteDatabase);
        addCategory(new ExpenseCategory("Gift"), sqLiteDatabase);
        addCategory(new ExpenseCategory("Medical"), sqLiteDatabase);
        addCategory(new ExpenseCategory("Health"), sqLiteDatabase);
        addCategory(new ExpenseCategory("Others"), sqLiteDatabase);
    }

    public ArrayList<ExpenseCategory> getCategory() {
        ArrayList<ExpenseCategory> categoryList = new ArrayList<>();
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();

            String query = "Select * from " + CATEGORY_TABLE_NAME;
            Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{});

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    int id = cursor.getInt(0);
                    String category = cursor.getString(1);

                    ExpenseCategory expenseCategory = new ExpenseCategory(category);
                    expenseCategory.setId(id);
                    categoryList.add(expenseCategory);
                }
            }

        } catch (Exception ex) {
            Log.d(TAG, "ExpenseDatabase: getCategory: " + ex);
        }
        return categoryList;
    }

    public ArrayList<ExpenseItem> getExpensesDetails() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ArrayList<ExpenseItem> expenseItemList = new ArrayList<>();
        String query = "Select * from " + EXPENSE_TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{});

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String desc = cursor.getString(1);
                int price = cursor.getInt(2);
                String category = cursor.getString(3);
                String time = cursor.getString(4);
                String notes = cursor.getString(5);

                ExpenseItem expenseItem = new ExpenseItem(desc, price, category, time);
                expenseItem.setId(id);
                expenseItem.setNote(notes);

                expenseItemList.add(expenseItem);
            }
        }
        return expenseItemList;
    }

    public boolean deleteData(long id) {
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            sqLiteDatabase.delete(EXPENSE_TABLE_NAME, COL_ID + "= ?", new String[]{"" + id});
            return true;
        } catch (Exception ex) {
            Log.d(TAG, "deleteData:" + ex);
            return false;
        }
    }

    public boolean addCategory(ExpenseCategory expenseCategory) {
        return addCategory(expenseCategory, getWritableDatabase());
    }

    private boolean addCategory(ExpenseCategory expenseCategory, SQLiteDatabase sqLiteDatabase) {
        ContentValues values = new ContentValues();
        values.put(COL_CATEGORIES, expenseCategory.getCategory());
        try {
            sqLiteDatabase.insert(CATEGORY_TABLE_NAME, null, values);
            return true;
        } catch (Exception ex) {
            Log.e(TAG, "addCategory " + ex.toString());
            return false;
        }
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

    public boolean updateExpense(ExpenseItem expenseItem) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COL_DESC, expenseItem.getDescription());
            values.put(COL_PRICE, expenseItem.getPrice());
            values.put(COL_CATEGORY, expenseItem.getCategory());
            values.put(COL_NOTES, expenseItem.getNote());
            db.update(EXPENSE_TABLE_NAME, values, COL_ID + " = ?", new String[]{"" + expenseItem.getId()});
            return true;
        } catch (Exception ex) {
            Log.d(TAG, "updateExpense: " + ex);
            return false;
        }
    }

    public boolean updateCategory(ExpenseCategory expenseCategory) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COL_CATEGORIES, expenseCategory.getCategory());

            db.update(CATEGORY_TABLE_NAME, values, COL_ID + " = ?", new String[]{"" + expenseCategory.getId()});
            return true;
        } catch (Exception ex) {
            Log.d(TAG, "updateExpense: " + ex);
            return false;
        }
    }

}
