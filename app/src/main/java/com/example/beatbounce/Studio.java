package com.example.beatbounce;

public class Studio {
    private String name;
    private int imageResourceId;
    private String location;
    private String price;
    private float rating;
    private boolean isFavorite;

    public Studio(String name, int imageResourceId, String location, String price, float rating) {
        this.name = name;
        this.imageResourceId = imageResourceId;
        this.location = location;
        this.price = price;
        this.rating = rating;
        this.isFavorite = false;
    }

    public String getName() {
        return name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String getLocation() {
        return location;
    }

    public String getPrice() {
        return price;
    }

    public float getRating() {
        return rating;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
