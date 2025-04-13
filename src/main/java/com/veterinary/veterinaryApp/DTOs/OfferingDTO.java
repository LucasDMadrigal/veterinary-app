package com.veterinary.veterinaryApp.DTOs;

import com.veterinary.veterinaryApp.models.Appointment;
import com.veterinary.veterinaryApp.models.Offering;
import com.veterinary.veterinaryApp.models.TimeSlot;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OfferingDTO {
	
	private long id;
	
	private String name;
	
	private String description;
	
	private double price;
	
	private String image;

	private Boolean active;
	
	private List<LocalDateTime> appointments; // Lista de citas de la oferta
	
//	private Set<AvailableSlotsDTO> availableSlots;
	private List<TimeSlotDTO> timeSlots;
	
	public OfferingDTO(Offering offering) {
		this.id = offering.getId();
		this.name = offering.getName();
		this.description = offering.getDescription();
		this.price = offering.getPrice();
		this.appointments = offering.getAppointments().stream().map(Appointment::getDateTime).toList(); // tengo mis dudas, creeería que hay que pasar los objetos completos de appointments para
		this.timeSlots = offering.getTimeSlots()
				.stream()
				.map(timeSlot -> new TimeSlotDTO(timeSlot))
				.toList();
		this.image = offering.getImage();
		this.active = offering.getActive();
	}
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public double getPrice() {
		return price;
	}

	public List<TimeSlotDTO> getTimeSlots() {
		return timeSlots;
	}

	public List<LocalDateTime> getAppointments() {
		return appointments;
	}
	
//	public Set<AvailableSlotsDTO> getAvailableSlots() {
//		return availableSlots;
//	}
	
	public String getImage() {
		return image;
	}

	public Boolean getActive() {
		return active;
	}
}
