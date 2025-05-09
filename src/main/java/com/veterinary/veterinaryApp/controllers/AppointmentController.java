package com.veterinary.veterinaryApp.controllers;

import com.veterinary.veterinaryApp.DTOs.AppointmentDTO;
import com.veterinary.veterinaryApp.DTOs.requestBodys.NewAppointmentDTO;
import com.veterinary.veterinaryApp.models.*;
import com.veterinary.veterinaryApp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api-veterinary/appointments")
public class AppointmentController {

  @Autowired
  private AppointmentService appointmentService;

  @Autowired
  private ClientService clientService;

  @Autowired
  private PetService petService;

  @Autowired
  private OfferingService offeringService;

  @Autowired
  private VeterinarianService veterinarianService;

  @Autowired
  private InvoiceService invoiceService;

//  @Autowired
//  private AvailableSlotsService availableSlotsService;

  @GetMapping("/")
  public ResponseEntity<?> getAllAppointments() {
    return ResponseEntity.ok(appointmentService.getAppointmentDTOs());
  }

  @PostMapping("/new")
  public ResponseEntity<?> createAppointment(@RequestBody NewAppointmentDTO newAppointmentDTO, Authentication authentication) {

    // cliente para asociar la cita
    Client client = clientService.getCurrentClient(authentication);

    // cuenta para asociar la invoice que se asocia a la cita
    Account account = client.getAccount();

    if (newAppointmentDTO.dateTime() == null) {
      return ResponseEntity.badRequest().body("The date and time field must not be empty");
    }

    // fechas para crear el turno
    long slotId = newAppointmentDTO.slotId();

    LocalDateTime dateTime = newAppointmentDTO.dateTime();
    LocalDateTime creationDate = LocalDateTime.now();

    String description = newAppointmentDTO.description();

    // status para crear el turno
    AppointmentStatus status = AppointmentStatus.valueOf(newAppointmentDTO.status().name().toUpperCase());

    // long petId para asociar una mascota a la cita y determinar el precio del servicio segun la mascota
    Long petId = newAppointmentDTO.petId();
    Pet pet = petService.getPetById(petId);
    if (pet == null) {
      return ResponseEntity.badRequest().body("Pet not found");
    }

    // Obtener servicio/oferta para la cita
    long offeringId = newAppointmentDTO.offeringId();
    Offering offering = offeringService.getOfferingById(offeringId);
    if (offering == null) {
      return ResponseEntity.badRequest().body("Offering not found");
    }

    // Calcular el costo del servicio según el tamaño de la mascota
    double baseRate = offering.getPrice();
    AnimalSize petSize = pet.getAnimalSize();
    double amountToCharge = offeringService.calculatePrice(petSize, baseRate);

    // Registro de depuración para asegurarse de que el precio se calcula correctamente
    System.out.println("Base rate: " + baseRate);
    System.out.println("Pet size: " + petSize);
    System.out.println("Amount to charge: " + amountToCharge);

    // Obtener el veterinario asignado de manera cíclica
    // Obtener todos los veterinarios disponibles
    List<Veterinarian> veterinarians = veterinarianService.getAllVeterinarians();
    if (veterinarians.isEmpty()) {
      return ResponseEntity.badRequest().body("No veterinarians available");
    }

    // Seleccionar un veterinario aleatorio
    Random random = new Random();
    int randomIndex = random.nextInt(veterinarians.size());
    Veterinarian veterinarian = veterinarians.get(randomIndex);

    if (veterinarian == null) {
      return ResponseEntity.badRequest().body("Veterinarian not found");
    }

    // Crear Invoice para asociarla a esa cita
    Invoice newInvoice = new Invoice(dateTime, amountToCharge, InvoiceStatus.PENDING);

    // Asociar Invoice a la cuenta
    newInvoice.setAccount(account);

    // Guardar Invoice en la base de datos
    invoiceService.saveInvoice(newInvoice);

    // Crear el turno y asociar con demás entidades
    Appointment newAppointment = new Appointment(dateTime, creationDate, description, status);

    // Asociar Invoice a la cita
    newInvoice.setAppointment(newAppointment);

    // asociar turno a client, pet, offering y veterinarian
    appointmentService.setEntities(newAppointment, client, pet, veterinarian, offering, newInvoice);

    // Guardar el turno en la base de datos
    appointmentService.saveAppointment(newAppointment);

    // Guardar el slot en la base de datos
    // Crear el DTO para devolver la respuesta
    AppointmentDTO newAppointmentObject = new AppointmentDTO(newAppointment);

    return new ResponseEntity<>(newAppointmentObject, HttpStatus.CREATED);
  }
}
