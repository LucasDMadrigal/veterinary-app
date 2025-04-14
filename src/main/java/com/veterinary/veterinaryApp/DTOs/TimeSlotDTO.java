package com.veterinary.veterinaryApp.DTOs;

import com.veterinary.veterinaryApp.models.Days;
import com.veterinary.veterinaryApp.models.TimeSlot;

public class TimeSlotDTO {
    private Days day;
    private String hour;
    private Boolean available;

    public TimeSlotDTO(TimeSlot timeSlot) {
        this.day = timeSlot.getDay();
        this.hour = timeSlot.getHour();
        this.available = timeSlot.getAvailable();
    }

    public Days getDay() {
        return day;
    }

    public String getHour() {
        return hour;
    }

    public Boolean getAvailable() {
        return available;
    }
}
