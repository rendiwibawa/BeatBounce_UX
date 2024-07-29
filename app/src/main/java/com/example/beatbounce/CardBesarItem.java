package com.example.beatbounce;

public class CardBesarItem {
    private final int imageResId;
    private final String title;
    private final String price;
    private final String location;
    private final String rating;

    public CardBesarItem(int imageResId, String title, String price, String location, String rating) {
        this.imageResId = imageResId;
        this.title = title;
        this.price = price;
        this.location = location;
        this.rating = rating;
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

    public String getLocation() {
        return location;
    }

    public String getRating() {
        return rating;
    }
}
