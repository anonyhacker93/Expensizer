package com.example.expensizer.viewmodel;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.expensizer.database.ExpenseDatabaseHelper;
import com.example.expensizer.model.ExpenseCategory;
import com.example.expensizer.model.ExpenseItem;

import java.util.ArrayList;

public class AddExpenseViewModel extends AndroidViewModel {
    ExpenseDatabaseHelper databaseHelper;
    Context applicationContext;

    public AddExpenseViewModel(@NonNull Application application) {
        super(application);
        init(application);
    }

    private void init(@NonNull Application application) {
        applicationContext = application.getApplicationContext();
        databaseHelper = ExpenseDatabaseHelper.getInstance(application.getApplicationContext());
    }

    public ArrayList<ExpenseCategory> getCategory() {
        return databaseHelper.getCategory();
    }

    public boolean validateInput(String descriptionText, String price) {
        if (descriptionText.isEmpty()) {
            Toast.makeText(applicationContext, "Desc is empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (price.isEmpty()) {
            Toast.makeText(applicationContext, "price is empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void addExpenseToDb(ExpenseItem expenseItem) {
        boolean isSuccess = databaseHelper.addExpense(expenseItem);
        if (isSuccess == true) {
            Toast.makeText(applicationContext, "Added successfully !", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(applicationContext, "Unable to add !", Toast.LENGTH_SHORT).show();
        }

    }

}
