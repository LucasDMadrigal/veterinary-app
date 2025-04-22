package com.veterinary.veterinaryApp.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("Veterinarian")
public class Veterinarian extends User {

    private String specialty;

    private String address;

    private String phone;

    private String image;

    private Boolean active = true;

    @OneToMany(mappedBy = "veterinarian", cascade = CascadeType.ALL)
    private List<Appointment> appointments = new ArrayList<>();

    public Veterinarian(String firstName, String lastName, String specialty, String address, String phone, String email, String password, String image, Boolean active) {
        super(email, password, firstName, lastName);
        this.specialty = specialty;
        this.address = address;
        this.phone = phone;
        this.image = image;
        this.active = active;
    }

    public Veterinarian() {
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }
    
    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    // Otros metodos
    public void addAppointment(Appointment appointment) {
        this.appointments.add(appointment);
        appointment.setVeterinarian(this);
    }
}
