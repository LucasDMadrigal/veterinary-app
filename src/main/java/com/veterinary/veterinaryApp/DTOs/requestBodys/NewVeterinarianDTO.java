package com.veterinary.veterinaryApp.DTOs.requestBodys;

public record NewVeterinarianDTO(String name, String specialty, String address, String phone, String email, String image, Boolean active) {
}
