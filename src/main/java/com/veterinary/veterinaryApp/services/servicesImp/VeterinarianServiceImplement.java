package com.veterinary.veterinaryApp.services.servicesImp;

import com.veterinary.veterinaryApp.DTOs.VeterinarianDTO;
import com.veterinary.veterinaryApp.DTOs.requestBodys.NewVeterinarianDTO;
import com.veterinary.veterinaryApp.Repositories.VeterinarianRepository;
import com.veterinary.veterinaryApp.models.Veterinarian;
import com.veterinary.veterinaryApp.services.VeterinarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeterinarianServiceImplement implements VeterinarianService {
	
	@Autowired
	VeterinarianRepository veterinarianRepository;
	
	@Override
	public List<Veterinarian> getAllVeterinarians() {
		return veterinarianRepository.findAll();
	}
	
	@Override
	public Veterinarian getVeterinarianById(Long id) {

		return veterinarianRepository.findById(id).orElse(null);
	}

	@Override
	public Veterinarian getVeterinarianByEmail(String email) {
		return veterinarianRepository.findByEmail(email);
	}

	@Override
	public List<VeterinarianDTO> getAllVeterinariansDTO() {
		return getAllVeterinarians().stream().map(VeterinarianDTO::new).toList();
	}
	
	@Override
	public Veterinarian createVeterinarian(NewVeterinarianDTO newVeterinarianDTO) {
		
		return new Veterinarian(
						newVeterinarianDTO.firstName(),
						newVeterinarianDTO.lastName(),
						newVeterinarianDTO.specialty(),
						newVeterinarianDTO.address(),
						newVeterinarianDTO.phone(),
						newVeterinarianDTO.email(),
						newVeterinarianDTO.password(),
						newVeterinarianDTO.image(),
				newVeterinarianDTO.active()
		);
	}
	
	@Override
	public void deleteVeterinarian(Veterinarian veterinarian) { veterinarianRepository.delete(veterinarian); }
	
	@Override
	public void saveVeterinarian(Veterinarian veterinarian) {
		veterinarianRepository.save(veterinarian);
	}
	
	
}
