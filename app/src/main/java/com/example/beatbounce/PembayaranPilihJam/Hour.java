package com.example.beatbounce.PembayaranPilihJam;

import java.io.Serializable;

public class Hour implements Serializable {
    private String time;
    private double price;
    private boolean isAvailable;

    public Hour(String time, double price, boolean isAvailable) {
        this.time = time;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public String getTime() {
        return time;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }



}
