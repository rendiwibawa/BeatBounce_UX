package com.example.beatbounce;

public class BookingHour {
    private String hour;
    private long priceHour;
    private boolean isAvailable;

    public BookingHour(String hour, long priceHour, boolean isAvailable) {
        this.hour = hour;
        this.priceHour = priceHour;
        this.isAvailable = isAvailable;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public long getPriceHour() {
        return priceHour;
    }

    public void setPriceHour(long priceHour) {
        this.priceHour = priceHour;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}