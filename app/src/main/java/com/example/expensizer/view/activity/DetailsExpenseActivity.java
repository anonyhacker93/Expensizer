package com.example.expensizer.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.example.expensizer.R;
import com.example.expensizer.databinding.ActivityDetailsExpenseBinding;
import com.example.expensizer.model.ExpenseItem;
import com.example.expensizer.viewmodel.DetailExpenseViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailsExpenseActivity extends AppCompatActivity {

    ActivityDetailsExpenseBinding binding;
    ExpenseItem expenseItem;
    DetailExpenseViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details_expense);
        viewModel = ViewModelProviders.of(this).get(DetailExpenseViewModel.class);
        Intent intent = getIntent();
        if (intent != null) {
            expenseItem = intent.getParcelableExtra("expensesItem");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy, HH:mm");
            String newDate = simpleDateFormat.format(new Date(expenseItem.getTime()));

            binding.descView.setText(expenseItem.getDescription());
            binding.priceView.setText("" + expenseItem.getPrice());
            binding.timeView.setText(newDate);
            binding.categoryView.setText(expenseItem.getCategory());

            if (!expenseItem.getNote().isEmpty()) {
                binding.notesView.setText(expenseItem.getNote());
            } else {
                binding.notesNameTextView.setVisibility(View.INVISIBLE);
            }


            binding.dltBtn.setOnClickListener((view) -> {
                Long id = expenseItem.getId();
                if (viewModel.deleteExpenseItem(id)) {
                    finish();
                }
            });
        }
    }


}
