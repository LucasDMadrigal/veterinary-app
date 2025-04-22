package com.veterinary.veterinaryApp.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("admin")
public class Admin extends User {

    public Admin() {
    }

    public Admin(String firtsName, String lastName, String email, String password) {
        super(email, password, firtsName, lastName);
    }

}
