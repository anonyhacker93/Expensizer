package com.example.expensizer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.expensizer.R;
import com.example.expensizer.database.ExpenseDatabaseHelper;
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
                deleteExpenseItem();
            });
        }
    }

    private void deleteExpenseItem() {
        ExpenseDatabaseHelper dbHelper = ExpenseDatabaseHelper.getInstance(this);
        Long id = expenseItem.getId();
        if (dbHelper.deleteData(id)) {
            Toast.makeText(this, getString(R.string.deleteItem), Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, getString(R.string.failItemDelete), Toast.LENGTH_SHORT).show();
        }
    }
}
