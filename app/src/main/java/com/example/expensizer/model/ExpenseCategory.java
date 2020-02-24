package com.example.expensizer.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ExpenseCategory implements Parcelable {
    String category;
    int id;

    public ExpenseCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    protected ExpenseCategory(Parcel in) {
        category = in.readString();
        id = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(category);
        dest.writeInt(id);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ExpenseCategory> CREATOR = new Parcelable.Creator<ExpenseCategory>() {
        @Override
        public ExpenseCategory createFromParcel(Parcel in) {
            return new ExpenseCategory(in);
        }

        @Override
        public ExpenseCategory[] newArray(int size) {
            return new ExpenseCategory[size];
        }
    };
}