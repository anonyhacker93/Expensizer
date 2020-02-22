package com.example.expensizer.model;


import android.os.Parcel;
import android.os.Parcelable;

public class ExpenseItem implements Parcelable {
    private long id;
    private String description;
    private double price;
    private String category;
    private String time;
    private String note;

    public ExpenseItem(String description, double price, String category, String time) {
        //this.id = -1;
        this.description = description;
        this.price = price;
        this.category = category;
        this.time = time;
        this.note = "";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    protected ExpenseItem(Parcel in) {
        id = in.readLong();
        description = in.readString();
        price = in.readDouble();
        category = in.readString();
        time = in.readString();
        note = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(description);
        dest.writeDouble(price);
        dest.writeString(category);
        dest.writeString(time);
        dest.writeString(note);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ExpenseItem> CREATOR = new Parcelable.Creator<ExpenseItem>() {
        @Override
        public ExpenseItem createFromParcel(Parcel in) {
            return new ExpenseItem(in);
        }

        @Override
        public ExpenseItem[] newArray(int size) {
            return new ExpenseItem[size];
        }
    };
}