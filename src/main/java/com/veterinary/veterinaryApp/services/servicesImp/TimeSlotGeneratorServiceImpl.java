package com.veterinary.veterinaryApp.services.servicesImp;

import com.veterinary.veterinaryApp.DTOs.requestBodys.TimeSlotGenerationRequestDTO;
import com.veterinary.veterinaryApp.Repositories.OfferingRepository;
import com.veterinary.veterinaryApp.models.Offering;
import com.veterinary.veterinaryApp.models.TimeSlot;
import com.veterinary.veterinaryApp.services.TimeSlotGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TimeSlotGeneratorServiceImpl implements TimeSlotGeneratorService {

    @Autowired
    private OfferingRepository offeringRepository;

    @Override
    public List<TimeSlot> generateSlots(TimeSlotGenerationRequestDTO timeSlotGenerationRequestDTO) {
        Offering offering = offeringRepository.findById(timeSlotGenerationRequestDTO.offeringId())
                .orElseThrow(() -> new RuntimeException("Offering not found"));

        LocalDate today = LocalDate.now();

        // Si no se especificó una fecha de fin, la calculamos
        LocalDate endDate = timeSlotGenerationRequestDTO.endDate();
        if (endDate == null) {
            endDate = calculateLastValidDate(timeSlotGenerationRequestDTO.timeSlots());
        }

        List<TimeSlot> generatedSlots = new ArrayList<>();
        List<TimeSlot> existingSlots = offering.getTimeSlots();

        for (LocalDate date = today; !date.isAfter(endDate); date = date.plusDays(1)) {
            DayOfWeek currentDay = date.getDayOfWeek();
//
//            for (TimeSlot slot : timeSlotGenerationRequestDTO.timeSlots()) {
//                if (slot.getDay().name().equalsIgnoreCase(currentDay.name())) {
//                    // Creamos un nuevo slot para esa fecha y hora
//                    generatedSlots.add(new TimeSlot(slot.getDay(), slot.getHour()));
//                }
//            }
//        }

        for (TimeSlot requestedSlot : timeSlotGenerationRequestDTO.timeSlots()) {
            if (requestedSlot.getDay().toString().equalsIgnoreCase(currentDay.toString())) {
                // Validación: ¿Ya existe un slot con mismo día y hora?
                boolean alreadyExists = existingSlots.stream().anyMatch(existing ->
                        existing.getDay().toString().equalsIgnoreCase(requestedSlot.getDay().toString()) &&
                                existing.getHour().equals(requestedSlot.getHour())
                );

                if (!alreadyExists) {
                    generatedSlots.add(new TimeSlot(requestedSlot.getDay(), requestedSlot.getHour()));
                }
            }
        }
    }

        // Los agregamos al offering
        offering.getTimeSlots().addAll(generatedSlots);
        offeringRepository.save(offering);

        return generatedSlots;
    }

    private LocalDate calculateLastValidDate(List<TimeSlot> timeSlots) {
        Set<DayOfWeek> allowedDays = timeSlots.stream()
                .map(ts -> DayOfWeek.valueOf(ts.getDay().toString().toUpperCase()))
                .collect(Collectors.toSet());

        LocalDate lastDayOfMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());

        // Vamos hacia atrás hasta encontrar un día válido
        while (!allowedDays.contains(lastDayOfMonth.getDayOfWeek())) {
            lastDayOfMonth = lastDayOfMonth.minusDays(1);
        }

        return lastDayOfMonth;
    }
}
