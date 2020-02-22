package com.example.expensizer.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.expensizer.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    Context applicationContext;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        applicationContext = application.getApplicationContext();
    }


    public List<String> getTabTitles() {
        List<String> titleList = new ArrayList<>();
        titleList.add(applicationContext.getString(R.string.home_tab));
        titleList.add(applicationContext.getString(R.string.show_expense_tab));
        return titleList;
    }
}
