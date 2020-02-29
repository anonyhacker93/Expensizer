package com.example.expensizer.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.expensizer.R;
import com.example.expensizer.adapter.ShowExpensesRecycleAdapter;
import com.example.expensizer.databinding.FragmentShowExpenseBinding;
import com.example.expensizer.model.ExpenseItem;
import com.example.expensizer.viewmodel.ShowExpenseViewModel;

import java.util.ArrayList;

//Show list of expenses
public class ShowExpenseFragment extends Fragment {

    FragmentShowExpenseBinding binding;
    ShowExpensesRecycleAdapter recycleAdapter;
    ShowExpenseViewModel viewModel;

    public ShowExpenseFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this).get(ShowExpenseViewModel.class);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_show_expense, container, false);

        setupRecyler();
        setupSearchView();
        return binding.getRoot();
    }

    private void setupSearchView() {
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recycleAdapter.filter(newText);
                return true;
            }
        });
    }


    private void setupRecyler() {
        ArrayList<ExpenseItem> expenseItemsList = viewModel.getExpenseDetails();
        recycleAdapter = new ShowExpensesRecycleAdapter(expenseItemsList, getContext());
        binding.showRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.showRecycleView.setAdapter(recycleAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (recycleAdapter != null) {
            ArrayList<ExpenseItem> expenseItemsList = viewModel.getExpenseDetails();
            recycleAdapter.setDataList(expenseItemsList);
            recycleAdapter.notifyDataSetChanged();
        }
    }
}
