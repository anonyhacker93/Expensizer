package com.example.expensizer.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.example.expensizer.R;
import com.example.expensizer.databinding.ActivityUpdateExpenseBinding;
import com.example.expensizer.model.ExpenseCategory;
import com.example.expensizer.model.ExpenseItem;
import com.example.expensizer.viewmodel.UpdateExpenseViewModel;

import java.util.ArrayList;

public class UpdateExpenseActivity extends AppCompatActivity {

    ActivityUpdateExpenseBinding updateExpenseBinding;
    ExpenseItem expenseItem;
    UpdateExpenseViewModel viewModel;
    ArrayList<String> categoryNameList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(UpdateExpenseViewModel.class);
        updateExpenseBinding = DataBindingUtil.setContentView(this, R.layout.activity_update_expense);
        loadSpinner();
        Intent intent = getIntent();
        if (intent != null) {
            expenseItem = intent.getParcelableExtra("updateList");

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
        String categoryName = expenseItem.getCategory();
        int pos = categoryNameList.indexOf(categoryName);
        updateExpenseBinding.categorySpinner.setSelection(pos);
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
        expenseItem.setCategory("" + updateExpenseBinding.categorySpinner.getSelectedItem());
        expenseItem.setNote(updateExpenseBinding.notesText.getText().toString());

        viewModel.updateExpenses(expenseItem);

        finish();
    }

    private void loadSpinner() {
        categoryNameList = new ArrayList<>();
        ArrayList<ExpenseCategory> expenseCategories = viewModel.getCategoty();

        for (int i = 0; i < expenseCategories.size(); i++) {
            String categoryName = expenseCategories.get(i).getCategory();

            categoryNameList.add(categoryName);

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoryNameList);
            dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            updateExpenseBinding.categorySpinner.setAdapter(dataAdapter);
            dataAdapter.notifyDataSetChanged();
        }
    }
}
