package com.veterinary.veterinaryApp.DTOs;

import com.veterinary.veterinaryApp.models.Appointment;
import com.veterinary.veterinaryApp.models.AppointmentStatus;
import com.veterinary.veterinaryApp.models.Client;
import com.veterinary.veterinaryApp.models.Pet;

import java.time.LocalDateTime;
import java.util.List;

public class ClientDTO {

  private Long id;

  private String firstName;

  private String lastName;

  private String email;

  private int phone;

  private AccountDTO account;

  private List<PetDTO> pets;

  private List<AppointmentDTO> confirmedAppointments;

  public ClientDTO(Client client) {

    List<Appointment> appointmentsAux = client.getAppointments();

    List<Pet> petsAux = client.getPets();

    this.id = client.getId();
    this.firstName = client.getFirstName();
    this.lastName = client.getLastName();
    this.email = client.getEmail();
    this.phone = client.getPhone();
    this.account = new AccountDTO(client.getAccount());
    this.pets = petsAux.stream().map(PetDTO::new).toList();
    this.confirmedAppointments = appointmentsAux.stream().map(AppointmentDTO::new).toList();
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

  public AccountDTO getAccount() {
    return account;
  }

  public int getPhone() {
    return phone;
  }

  public List<PetDTO> getPets() {
    return pets;
  }

  public List<AppointmentDTO> getConfirmedAppointments() {
    return confirmedAppointments;
  }
}
