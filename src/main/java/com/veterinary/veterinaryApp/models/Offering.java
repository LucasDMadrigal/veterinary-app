package com.veterinary.veterinaryApp.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Offering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String description;

    private double price;
    
    private String image;

    @OneToMany(mappedBy = "offering")
    private List<Appointment> appointments = new ArrayList<>(); // contiene los bloques horarios reservados
//
//    @OneToMany(mappedBy = "offering")
//    private List<AvailableSlots> availableSlots = new ArrayList<>(); // contiene los bloques horarios disponibles


    @ElementCollection
    @Column(name="timeSlots")
    private List<TimeSlot> timeSlots = new ArrayList<>();
    // CONSTRUCTORES

    public Offering() {
    }

//    public Offering(String image, double price, String description, String name, long id, List<TimeSlot> timeSlots) {
//        this.image = image;
//        this.price = price;
//        this.description = description;
//        this.name = name;
//        this.id = id;
//        this.timeSlots = timeSlots;
//    }


    public Offering(String name, String description, double price, String image, List<TimeSlot> timeSlots) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.timeSlots = timeSlots;
    }

    // GETTERS Y SETTERS
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }
//
    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
//    // Otros metodos
    public void addAppointment(Appointment appointment) {
        this.appointments.add(appointment);
        appointment.setOffering(this);
    }



    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public List<TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(List<TimeSlot> timeSlots) {
        this.timeSlots = timeSlots;
    }
}
