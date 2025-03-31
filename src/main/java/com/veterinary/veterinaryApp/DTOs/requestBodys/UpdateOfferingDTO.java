package com.veterinary.veterinaryApp.DTOs.requestBodys;

import com.veterinary.veterinaryApp.models.TimeSlot;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UpdateOfferingDTO (@NotNull long id, @NotNull double price, @NotNull String name, @NotNull String description,
    @NotNull String image, @NotNull List<TimeSlot> timeSlots
) {
}
