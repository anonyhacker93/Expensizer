package com.example.expensizer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.expensizer.database.ExpenseDatabaseHelper;
import com.example.expensizer.databinding.ActivityUpdateExpenseBinding;
import com.example.expensizer.model.ExpenseItem;

public class UpdateExpenseActivity extends AppCompatActivity {

    ActivityUpdateExpenseBinding updateExpenseBinding;
    ExpenseItem expenseItem;
    ExpenseDatabaseHelper dbHelper;//PAGAL KR DIYA RE

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateExpenseBinding = DataBindingUtil.setContentView(this, R.layout.activity_update_expense);
        Intent intent = getIntent();
        if (intent != null) {
            expenseItem = (ExpenseItem) intent.getParcelableExtra("updateList");
            setExpenseInfoOnView(expenseItem);
            updateExpenseBinding.updateBtn.setOnClickListener((View view) -> {
                        updateExpense(expenseItem);
                    }
            );
        }
    }

    private void setExpenseInfoOnView(ExpenseItem expenseItem) {
        updateExpenseBinding.descText.setText(expenseItem.getDescription());
        updateExpenseBinding.priceText.setText("" + expenseItem.getPrice());
        updateExpenseBinding.categoryText.setText(expenseItem.getCategory());
        String notes = expenseItem.getNote();
        if (notes != null) {
            updateExpenseBinding.notesText.setText(notes);
        }
    }

    private void updateExpense(ExpenseItem expenseItem) {
        expenseItem.setDescription(updateExpenseBinding.descText.getText().toString());
        String price = updateExpenseBinding.priceText.getText().toString();
        Double priceText = Double.parseDouble(price);
        expenseItem.setPrice(priceText);
        expenseItem.setCategory(updateExpenseBinding.categoryText.getText().toString());
        expenseItem.setNote(updateExpenseBinding.notesText.getText().toString());

        dbHelper = ExpenseDatabaseHelper.getInstance(getApplicationContext());
        if (dbHelper.updateExpense(expenseItem)) {
            Toast.makeText(UpdateExpenseActivity.this, getString(R.string.updateSuccessMsg), Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(UpdateExpenseActivity.this, getString(R.string.updateFailMsg), Toast.LENGTH_SHORT).show();
        }
    }
}
