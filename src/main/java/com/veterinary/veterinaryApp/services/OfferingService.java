package com.veterinary.veterinaryApp.services;

import com.veterinary.veterinaryApp.DTOs.OfferingDTO;
import com.veterinary.veterinaryApp.DTOs.requestBodys.NewOfferingDTO;
import com.veterinary.veterinaryApp.models.AnimalSize;
import com.veterinary.veterinaryApp.models.Offering;

import java.util.List;

public interface OfferingService {

    List<Offering> getAllOfferings();

    List<OfferingDTO> getAllOfferingsDTO();

    Offering getOfferingById(long id);

    void saveOffering(Offering offering);

    double calculatePrice(AnimalSize petSize, double baseRate);

    void updateOffering(Offering offering);

    Offering createOffering(NewOfferingDTO newOffering);
    
    void deleteOfferingById(long id);
}