package com.example.expensizer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.expensizer.database.ExpenseDatabaseHelper;
import com.example.expensizer.databinding.ActivityAddExpensesBinding;
import com.example.expensizer.model.ExpenseItem;

import java.util.Date;

public class AddExpensesActivity extends AppCompatActivity {

    ExpenseDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenses);
        ActivityAddExpensesBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_add_expenses);

        final EditText descText = findViewById(R.id.descText);
        final EditText mnyText = findViewById(R.id.moneyText);
        Button saveBtn = findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelper = ExpenseDatabaseHelper.getInstance(getApplicationContext());
                String descriptionText = descText.getText().toString();
                String price = mnyText.getText().toString();

                if (!validateInput(descriptionText, price)) {
                    return;
                }
                long moneyText = Integer.parseInt(price);
                String curTime = new Date().toString();
                ExpenseItem expenseItem = new ExpenseItem(descriptionText, moneyText, "General", curTime);

                boolean isSuccess = databaseHelper.addExpense(expenseItem);
                if (isSuccess == true) {
                    Toast.makeText(AddExpensesActivity.this, "Added successfully !", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddExpensesActivity.this, "Unable to add !", Toast.LENGTH_SHORT).show();
                }
                finish();

            }
        });

    }

    private boolean validateInput(String descriptionText, String price) {
        if (descriptionText.isEmpty()) {
            Toast.makeText(AddExpensesActivity.this, "Desc is empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (price.isEmpty()) {
            Toast.makeText(AddExpensesActivity.this, "price is empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
