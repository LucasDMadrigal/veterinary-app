package com.veterinary.veterinaryApp.models;

import jakarta.persistence.Embeddable;

    @Embeddable
public class TimeSlot {
    private Days day;
    private String hour;
    private Boolean available;


    public TimeSlot() {
    }

    public TimeSlot(Days day, String hour) {
        this.day = day;
        this.hour = hour;
        this.available = true;
    }

    public Days getDay() {
        return day;
    }

    public void setDay(Days day) {
        this.day = day;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

        public Boolean getAvailable() {
            return available;
        }

        public void setAvailable(Boolean available) {
            this.available = available;
        }
    }
