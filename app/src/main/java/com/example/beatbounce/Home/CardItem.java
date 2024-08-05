package com.example.beatbounce.Home;

// CardItem.java
public class CardItem {
    private final int imageResId;
    private final String title;
    private final String price;

    public CardItem(int imageResId, String title, String price) {
        this.imageResId = imageResId;
        this.title = title;
        this.price = price;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }



}
