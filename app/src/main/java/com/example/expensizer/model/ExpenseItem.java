package com.example.expensizer.model;

public class ExpenseItem {
    private long id;
    private String description;
    private double price;
    private String category;
    private long time;
    private String note;

    public ExpenseItem(String description, double price, String category, long time) {
        this.id = -1;
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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
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
}
