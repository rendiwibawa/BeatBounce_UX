package com.example.beatbounce.PembayaranPilihJam;

import java.util.List;

public class BookingDate {
    private String text;
    private List<Hour> hours;

    public BookingDate(String text, List<Hour> hours) {
        this.text = text;
        this.hours = hours;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Hour> getHours() {
        return hours;
    }

    public void setHours(List<Hour> hours) {
        this.hours = hours;
    }
}