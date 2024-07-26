package com.example.beatbounce;

import java.util.List;

public class BookingDate {
    private String text;
    private List<BookingHour> hours;

    public BookingDate(String text, List<BookingHour> hours) {
        this.text = text;
        this.hours = hours;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<BookingHour> getHours() {
        return hours;
    }

    public void setHours(List<BookingHour> hours) {
        this.hours = hours;
    }
}