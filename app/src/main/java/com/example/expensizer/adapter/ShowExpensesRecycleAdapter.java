package com.example.expensizer.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensizer.R;
import com.example.expensizer.database.ExpenseDatabaseHelper;
import com.example.expensizer.databinding.RowShowExpensesLayoutBinding;
import com.example.expensizer.model.ExpenseCategory;
import com.example.expensizer.model.ExpenseItem;
import com.example.expensizer.view.activity.DetailsExpenseActivity;
import com.example.expensizer.view.activity.UpdateExpenseActivity;

import java.util.ArrayList;

public class ShowExpensesRecycleAdapter extends RecyclerView.Adapter {

    ArrayList<ExpenseItem> expenseItemArrayList;
    RowShowExpensesLayoutBinding showExpensesLayoutBinding;
    Context context;
    ArrayList<ExpenseCategory> expenseCategoriesList;
    ExpenseDatabaseHelper dbHelper;

    public ShowExpensesRecycleAdapter(ArrayList<ExpenseItem> expenseItemArrayList, ArrayList<ExpenseCategory> expenseCategoriesList, Context context) {
        this.expenseItemArrayList = expenseItemArrayList;
        this.context = context;
        this.expenseCategoriesList = expenseCategoriesList;
    }

    public void setDataList(ArrayList<ExpenseItem> expenseItemArrayList) {
        this.expenseItemArrayList = expenseItemArrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        showExpensesLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.row_show_expenses_layout, parent, false);
        return new MyViewHolder(showExpensesLayoutBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final ExpenseItem expenseItem = expenseItemArrayList.get(position);
        final MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.showExpensesLayoutBinding.descView.setText(expenseItem.getDescription());
        myViewHolder.showExpensesLayoutBinding.priceView.setText("" + expenseItem.getPrice());

        myViewHolder.showExpensesLayoutBinding.expenseRow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(context, UpdateExpenseActivity.class);
                intent.putExtra("updateList", expenseItem);
                context.startActivity(intent);
                return true;
            }
        });

        myViewHolder.showExpensesLayoutBinding.expenseRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, DetailsExpenseActivity.class);
                intent.putExtra("expensesItem", expenseItem);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return expenseItemArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        RowShowExpensesLayoutBinding showExpensesLayoutBinding;

        public MyViewHolder(RowShowExpensesLayoutBinding showExpensesLayoutBinding) {
            super(showExpensesLayoutBinding.getRoot());
            this.showExpensesLayoutBinding = showExpensesLayoutBinding;
        }
    }

}