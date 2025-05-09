package com.veterinary.veterinaryApp.DTOs.requestBodys;

import com.veterinary.veterinaryApp.models.TimeSlot;

import java.time.LocalDate;
import java.util.List;

public record TimeSlotGenerationRequestDTO(Long offeringId, LocalDate endDate, List<TimeSlot> timeSlots) {
}
