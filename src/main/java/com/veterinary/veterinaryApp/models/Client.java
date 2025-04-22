package com.veterinary.veterinaryApp.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("client")
public class Client extends User {

  int phone;

  @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
  private Account account;

  @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
  private List<Pet> pets = new ArrayList<>();

  @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
  private List<Appointment> appointments = new ArrayList<>();

  // Constructores
  public Client() {
  }

  public Client(String firstName, String lastName, String email, String password, int phone) {
    super(email, password, firstName, lastName);
    this.phone = phone;
  }

  // Métodos accesores

  public int getPhone() {
    return phone;
  }

  public void setPhone(int phone) {
    this.phone = phone;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public List<Appointment>  getAppointments() {
    return appointments;
  }

  public void setAppointments(List<Appointment> appointments) {
    this.appointments = appointments;
  }

  public List<Pet> getPets() {
    return pets;
  }

  public void setPets(List<Pet> pets) {
    this.pets = pets;
  }

  // Otros metodos
  public void addAppointment(Appointment appointment) {
    this.appointments.add(appointment);
    appointment.setClient(this);
  }

  public void addPet(Pet pet) {
    pet.setOwner(this);
    pets.add(pet);
  }

//  @Override
//  public String toString() {
//    return "User [id=" + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
//        + ", password=" + ", phone=" + phone + ", admin=" + "]";
//  }
}
