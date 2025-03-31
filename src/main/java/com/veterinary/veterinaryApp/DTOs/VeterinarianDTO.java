package com.veterinary.veterinaryApp.DTOs;

import com.veterinary.veterinaryApp.models.Appointment;
import com.veterinary.veterinaryApp.models.Veterinarian;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class VeterinarianDTO {

    private Long id;

    private String name;

    private String specialty;

    private String address;

    private String phone;

    private String email;

    private Boolean active = true;

    private List<LocalDateTime> appointments = new ArrayList<>();
    
    private String image;

    public VeterinarianDTO() {}

    public VeterinarianDTO(Veterinarian veterinarian) {
        this.id = veterinarian.getId();
        this.name = veterinarian.getName();
        this.specialty = veterinarian.getSpecialty();
        this.address = veterinarian.getAddress();
        this.phone = veterinarian.getPhone();
        this.email = veterinarian.getEmail();
        this.appointments = veterinarian.getAppointments().stream().map(Appointment::getDateTime).toList();
        this.image = veterinarian.getImage();
        this.active = veterinarian.getActive();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getSpecialty() {
        return specialty;
    }

    public List<LocalDateTime> getAppointments() {
        return appointments;
    }
    
    public String getImage() {
        return image;
    }
}
