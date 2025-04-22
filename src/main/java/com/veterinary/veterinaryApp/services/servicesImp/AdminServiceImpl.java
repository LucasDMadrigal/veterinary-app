package com.veterinary.veterinaryApp.services.servicesImp;

import com.veterinary.veterinaryApp.DTOs.AdminDTO;
import com.veterinary.veterinaryApp.Repositories.AdminRepostory;
import com.veterinary.veterinaryApp.models.Admin;
import com.veterinary.veterinaryApp.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepostory adminRepostory;

    @Override
    public void saveAdmin(Admin admin) {
        adminRepostory.save(admin);
    }

    @Override
    public Admin getAdminByEmail(String email) {
        return adminRepostory.findByEmail(email);
    }

    @Override
    public Admin getAdminById(Long id) {
        return adminRepostory.findById(id).orElse(null);
    }

}
