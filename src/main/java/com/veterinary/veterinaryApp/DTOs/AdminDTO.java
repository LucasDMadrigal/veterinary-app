package com.veterinary.veterinaryApp.DTOs;

import com.veterinary.veterinaryApp.models.Admin;

public class AdminDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public AdminDTO(Admin admin) {
        this.id = admin.getId();
        this.firstName = admin.getFirstName();
        this.lastName = admin.getLastName();
        this.email = admin.getEmail();
        this.password = admin.getPassword();
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
