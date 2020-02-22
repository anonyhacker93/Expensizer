package com.example.expensizer.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.example.expensizer.R;
import com.example.expensizer.databinding.ActivityAddCategoryBinding;
import com.example.expensizer.model.ExpenseCategory;
import com.example.expensizer.viewmodel.AddCategoryViewModel;

public class AddCategoryActivity extends AppCompatActivity {

    ActivityAddCategoryBinding binding;
    AddCategoryViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(AddCategoryViewModel.class);

        binding = DataBindingUtil.setContentView(AddCategoryActivity.this, R.layout.activity_add_category);
        EditText addText = binding.addText;
        Button addCategoryBtn = binding.addCategoryBtn;

        addCategoryBtn.setOnClickListener((View view) -> {
            String categgory = addText.getText().toString();
            if (!categgory.isEmpty()) {
                ExpenseCategory expenseCategory = new ExpenseCategory(categgory);
                viewModel.addCategory(expenseCategory);
                finish();
            } else {
                Toast.makeText(AddCategoryActivity.this, "Please enter Category", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

