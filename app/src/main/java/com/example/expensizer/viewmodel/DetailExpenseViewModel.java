package com.example.expensizer.viewmodel;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.expensizer.R;
import com.example.expensizer.database.ExpenseDatabaseHelper;

public class DetailExpenseViewModel extends AndroidViewModel {
    Context applicationContext;

    public DetailExpenseViewModel(@NonNull Application application) {
        super(application);
        init(application);
    }

    private void init(Application application) {
        applicationContext = application.getApplicationContext();
    }

    public boolean deleteExpenseItem(Long id) {
        ExpenseDatabaseHelper dbHelper = ExpenseDatabaseHelper.getInstance(applicationContext);

        if (dbHelper.deleteData(id)) {
            Toast.makeText(applicationContext, applicationContext.getString(R.string.deleteItem), Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(applicationContext, applicationContext.getString(R.string.failItemDelete), Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
