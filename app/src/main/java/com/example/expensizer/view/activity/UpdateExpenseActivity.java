package com.example.expensizer.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.example.expensizer.R;
import com.example.expensizer.databinding.ActivityUpdateExpenseBinding;
import com.example.expensizer.model.ExpenseItem;
import com.example.expensizer.viewmodel.UpdateExpenseViewModel;

public class UpdateExpenseActivity extends AppCompatActivity {

    ActivityUpdateExpenseBinding updateExpenseBinding;
    ExpenseItem expenseItem;
    UpdateExpenseViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(UpdateExpenseViewModel.class);
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

        viewModel.updateExpenses(expenseItem);
        finish();
    }
}
