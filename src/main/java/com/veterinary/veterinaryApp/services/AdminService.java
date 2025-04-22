package com.veterinary.veterinaryApp.services;

import com.veterinary.veterinaryApp.DTOs.AdminDTO;
import com.veterinary.veterinaryApp.models.Admin;

public interface AdminService {
    void saveAdmin(Admin admin);
    Admin getAdminByEmail(String email);
    Admin getAdminById(Long id);

}
