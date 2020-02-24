package com.example.expensizer.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.expensizer.database.ExpenseDatabaseHelper;
import com.example.expensizer.model.ExpenseCategory;
import com.example.expensizer.model.ExpenseItem;

import java.util.ArrayList;

public class ShowExpenseViewModel extends AndroidViewModel {

    Context applicationContext;

    public ShowExpenseViewModel(@NonNull Application application) {
        super(application);
        init(application);
    }

    private void init(@NonNull Application application) {
        applicationContext = application.getApplicationContext();
    }

    public ArrayList<ExpenseItem> getExpenseDetails() {
        ExpenseDatabaseHelper databaseHelper = ExpenseDatabaseHelper.getInstance(getApplication());
        return databaseHelper.getExpensesDetails();
    }

    public ArrayList<ExpenseCategory> getExpenseCategory() {
        ExpenseDatabaseHelper databaseHelper = ExpenseDatabaseHelper.getInstance(getApplication());
        return databaseHelper.getCategory();
    }
}
