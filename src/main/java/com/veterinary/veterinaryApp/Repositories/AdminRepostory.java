package com.veterinary.veterinaryApp.Repositories;

import com.veterinary.veterinaryApp.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepostory extends JpaRepository<Admin, Long> {
    Admin findByEmail(String email);
    Optional<Admin> findById(Long id);
}
