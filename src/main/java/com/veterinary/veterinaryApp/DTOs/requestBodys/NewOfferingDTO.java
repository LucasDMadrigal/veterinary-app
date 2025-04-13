package com.veterinary.veterinaryApp.DTOs.requestBodys;

import com.veterinary.veterinaryApp.models.TimeSlot;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record NewOfferingDTO(@NotNull String name, @NotNull String description, @NotNull double price,
                             String image, Boolean active, List<TimeSlot> timeSlots) {


}
