package com.veterinary.veterinaryApp.DTOs.requestBodys;

import jakarta.validation.constraints.NotNull;

public record updateVeterinarianDTO(@NotNull Long id, String firstName, String lastName, String specialty, String address, String phone, String email, String image, Boolean active) {
}