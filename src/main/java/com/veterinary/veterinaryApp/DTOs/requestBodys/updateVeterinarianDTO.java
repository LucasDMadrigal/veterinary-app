package com.veterinary.veterinaryApp.DTOs.requestBodys;

import jakarta.validation.constraints.NotNull;

public record updateVeterinarianDTO(@NotNull Long id, String name, String specialty, String address, String phone, String email, String image, Boolean active) {
}