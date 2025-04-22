package com.veterinary.veterinaryApp.Repositories;

import com.veterinary.veterinaryApp.models.Veterinarian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeterinarianRepository extends JpaRepository<Veterinarian, Long> {

  Veterinarian findById(long id);
  Veterinarian findByEmail(String email);

}
