package com.example.expensizer.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.expensizer.R;
import com.example.expensizer.adapter.ShowExpensesRecycleAdapter;
import com.example.expensizer.database.ExpenseDatabaseHelper;
import com.example.expensizer.databinding.FragmentShowExpenseBinding;
import com.example.expensizer.model.ExpenseItem;

import java.util.ArrayList;

//Show list of expenses
public class ShowExpenseFragment extends Fragment {
    ExpenseDatabaseHelper databaseHelper;
    FragmentShowExpenseBinding binding;
    ShowExpensesRecycleAdapter recycleAdapter;

    public ShowExpenseFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        databaseHelper = ExpenseDatabaseHelper.getInstance(getContext());

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_show_expense, container, false);

        setupRecyler();

        return binding.getRoot();
    }


    private void setupRecyler() {
        ArrayList<ExpenseItem> expenseItemsList = databaseHelper.getExpensesDetails();

        recycleAdapter = new ShowExpensesRecycleAdapter(expenseItemsList, getContext());
        binding.showRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.showRecycleView.setAdapter(recycleAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (recycleAdapter != null) {
            ArrayList<ExpenseItem> expenseItemsList = databaseHelper.getExpensesDetails();
            recycleAdapter.setDataList(expenseItemsList);
            recycleAdapter.notifyDataSetChanged();
        }
    }
}
