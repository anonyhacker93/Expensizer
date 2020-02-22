package com.example.expensizer.viewmodel;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.expensizer.database.ExpenseDatabaseHelper;
import com.example.expensizer.model.ExpenseCategory;

public class AddCategoryViewModel extends AndroidViewModel {

    Context applicationContext;

    public AddCategoryViewModel(@NonNull Application application) {
        super(application);
        init(application);
    }

    private void init(@NonNull Application application) {
        applicationContext = application.getApplicationContext();
    }

    public void addCategory(ExpenseCategory expenseCategory) {
        ExpenseDatabaseHelper expenseDatabaseHelper = ExpenseDatabaseHelper.getInstance(getApplication());
        if (expenseDatabaseHelper.addCategory(expenseCategory)) {

            Toast.makeText(applicationContext, "Successfully Added", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(applicationContext, "failed to Added", Toast.LENGTH_SHORT).show();
        }
    }
}

