package com.example.beatbounce.Home;

import java.util.List;

public class Studio {
    private int imageResource;
    private String name;
    private String price;
    private String owner;
    private List<Integer> facilities;
    private List<String> genres;
    private double rating;
    private String location;
    private boolean isFavorite;


    public Studio(int imageResource, String name, String price, String owner, List<Integer> facilities, List<String> genres, double rating, String location) {
        this.imageResource = imageResource;
        this.name = name;
        this.price = price;
        this.owner = owner;
        this.facilities = facilities;
        this.genres = genres;
        this.rating = rating;
        this.location = location;
        this.isFavorite = false;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<Integer> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<Integer> facilities) {
        this.facilities = facilities;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isFavorite() {
        return isFavorite;
    }
    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}