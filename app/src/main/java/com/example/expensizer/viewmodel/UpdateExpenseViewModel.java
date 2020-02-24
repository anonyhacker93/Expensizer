package com.example.expensizer.viewmodel;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.expensizer.R;
import com.example.expensizer.database.ExpenseDatabaseHelper;
import com.example.expensizer.model.ExpenseItem;

public class UpdateExpenseViewModel extends AndroidViewModel {

    Context applicationContext;
    ExpenseDatabaseHelper dbHelper;

    public UpdateExpenseViewModel(@NonNull Application application) {
        super(application);
        applicationContext = application.getApplicationContext();
    }

    public void updateExpenses(ExpenseItem expenseItem) {
        dbHelper = ExpenseDatabaseHelper.getInstance(getApplication());
        if (dbHelper.updateExpense(expenseItem)) {
            Toast.makeText(applicationContext, applicationContext.getString(R.string.updateSuccessMsg), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(applicationContext, applicationContext.getString(R.string.updateFailMsg), Toast.LENGTH_SHORT).show();
        }
    }


}
