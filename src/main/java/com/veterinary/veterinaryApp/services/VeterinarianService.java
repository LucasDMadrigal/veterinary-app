package com.veterinary.veterinaryApp.services;

import com.veterinary.veterinaryApp.DTOs.VeterinarianDTO;
import com.veterinary.veterinaryApp.DTOs.requestBodys.NewVeterinarianDTO;
import com.veterinary.veterinaryApp.models.Admin;
import com.veterinary.veterinaryApp.models.Veterinarian;

import java.util.List;

public interface VeterinarianService {
	
	List<Veterinarian> getAllVeterinarians();
	
	Veterinarian getVeterinarianById(Long id);

	Veterinarian getVeterinarianByEmail(String email);

	List<VeterinarianDTO> getAllVeterinariansDTO();
	
	void saveVeterinarian(Veterinarian veterinarian);
	
	Veterinarian createVeterinarian(NewVeterinarianDTO newVeterinarianDTO);
	
	void deleteVeterinarian(Veterinarian veterinarian);
}