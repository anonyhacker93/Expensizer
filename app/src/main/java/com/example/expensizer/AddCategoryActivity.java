package com.example.expensizer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.expensizer.database.ExpenseDatabaseHelper;
import com.example.expensizer.databinding.ActivityAddCategoryBinding;
import com.example.expensizer.model.ExpenseCategory;

public class AddCategoryActivity extends AppCompatActivity {

    ActivityAddCategoryBinding binding;
    ExpenseDatabaseHelper expenseDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(AddCategoryActivity.this, R.layout.activity_add_category);
        EditText addText = binding.addText;
        Button addCategoryBtn = binding.addCategoryBtn;

        addCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String categgory = addText.getText().toString();
                if (!categgory.isEmpty()) {
                    ExpenseCategory expenseCategory = new ExpenseCategory(categgory);
                    expenseDatabaseHelper = ExpenseDatabaseHelper.getInstance(getApplicationContext());
                    if (expenseDatabaseHelper.addCategory(expenseCategory)) {
                        finish();
                        Toast.makeText(AddCategoryActivity.this, "Successfully Added", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddCategoryActivity.this, "failed to Added", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AddCategoryActivity.this, "Please enter Category", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
