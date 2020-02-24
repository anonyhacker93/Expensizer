package com.example.expensizer.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.expensizer.R;
import com.example.expensizer.model.ExpenseCategory;
import com.example.expensizer.model.ExpenseItem;
import com.example.expensizer.viewmodel.AddExpenseViewModel;

import java.util.ArrayList;
import java.util.Date;

public class AddExpensesActivity extends AppCompatActivity {

    Spinner spinner;
    AddExpenseViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenses);
        // ActivityAddExpensesBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_add_expenses);

        final EditText descText = findViewById(R.id.descText);
        final EditText mnyText = findViewById(R.id.moneyText);
        spinner = findViewById(R.id.spinner);
        Button saveBtn = findViewById(R.id.saveBtn);
        init(descText, mnyText, saveBtn);
    }


    private void init(EditText descText, EditText mnyText, Button saveBtn) {
        viewModel = ViewModelProviders.of(this).get(AddExpenseViewModel.class);
        loadSpinnerData();
        saveBtn.setOnClickListener((View view) -> {
            String descriptionText = descText.getText().toString();
            String price = mnyText.getText().toString();
            Object objCategory = spinner.getSelectedItem();
            if (!viewModel.validateInput(descriptionText, price, objCategory)) {
                return;
            }

            long moneyText = Integer.parseInt(price);
            String curTime = new Date().toString();

            ExpenseItem expenseItem = new ExpenseItem(descriptionText, moneyText, objCategory.toString(), curTime);
            viewModel.addExpenseToDb(expenseItem);
            finish();
        });
    }

    private void loadSpinnerData() {
        ArrayList<String> list = new ArrayList<>();
        ArrayList<ExpenseCategory> expenseCategories = viewModel.getCategory();

        for (int i = 0; i < expenseCategories.size(); i++) {
            ExpenseCategory expenseCategory = expenseCategories.get(i);
            list.add(expenseCategory.getCategory());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }
}
