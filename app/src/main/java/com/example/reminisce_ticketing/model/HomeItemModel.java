package com.example.reminisce_ticketing.model;

public class HomeItemModel {
    private String title;
    private String description;

    public HomeItemModel(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
