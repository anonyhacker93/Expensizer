package com.example.expensizer;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.expensizer.databinding.ActivityDetailsExpenseBinding;
import com.example.expensizer.model.ExpenseItem;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailsExpenseActivity extends AppCompatActivity {

    ActivityDetailsExpenseBinding binding;
    ExpenseItem expenseItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details_expense);

        Intent intent = getIntent();
        if (intent != null) {
            expenseItem = (ExpenseItem) intent.getSerializableExtra("expensesItem");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy, HH:mm");
            String newDate = simpleDateFormat.format(new Date(expenseItem.getTime()));

            binding.descView.setText(expenseItem.getDescription());
            binding.priceView.setText("" + expenseItem.getPrice());
            binding.timeView.setText(newDate);
            binding.categoryView.setText(expenseItem.getCategory());
            binding.notesView.setText(expenseItem.getNote());
        }


    }
}
