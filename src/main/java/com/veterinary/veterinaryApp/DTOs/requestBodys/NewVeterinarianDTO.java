package com.veterinary.veterinaryApp.DTOs.requestBodys;

public record NewVeterinarianDTO(String firstName,String lastName, String specialty, String address, String phone, String email, String password, String image, Boolean active) {
}
