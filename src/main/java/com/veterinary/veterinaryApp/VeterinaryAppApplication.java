package com.veterinary.veterinaryApp;

import com.veterinary.veterinaryApp.Repositories.*;
import com.veterinary.veterinaryApp.models.*;
import com.veterinary.veterinaryApp.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.veterinary.veterinaryApp.utils.Utils.fiveDigits;

@SpringBootApplication
public class VeterinaryAppApplication {


    @Autowired
    PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(VeterinaryAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner initDatabase(
            ClientRepository clientRepository, AccountRepository accountRepository,
            PetRepository petRepository, VeterinarianRepository veterinarianRepository,
            OfferingRepository offeringRepository, AppointmentRepository appointmentRepository,
            InvoiceRepository invoiceRepository, AdminService adminService
    ) {
        return (args) -> {
//
            // creacion de usuarios
            Admin userAdmin = new Admin("Jelena",
                    "Palavecino",
                    "jelena@vetadmin.com",
                    passwordEncoder.encode("abc123")
            );

            Client lucas = new Client("Daniel", "Madrigal", "lucas@mail.com", passwordEncoder.encode("123"), 51258927);

            Client pedro = new Client("Pedro", "Sanabria", "pedro@sanabria.com", passwordEncoder.encode("123"), 51258927);

            Client leonel = new Client("Leonel", "Borjas", "leonel@borjas.com", passwordEncoder.encode("123"), 51258927);

            adminService.saveAdmin(userAdmin);
            clientRepository.save(lucas);
            clientRepository.save(pedro);
            clientRepository.save(leonel);

            // creacion de cuentas
            Account account1 = new Account(1000, fiveDigits());
            Account account2 = new Account(1500, fiveDigits());
            Account account3 = new Account(2000, fiveDigits());
            Account account4 = new Account(2500, fiveDigits());

//            account1.setClient(userAdmin);
            account2.setClient(lucas);
            account3.setClient(pedro);
            account4.setClient(leonel);

            accountRepository.save(account1);
            accountRepository.save(account2);
            accountRepository.save(account3);
            accountRepository.save(account4);

            // Creación de Pets
            Pet pet1 = new Pet("Pulguita", "5 años", "Perro", "Chihuahua", AnimalSize.SMALL, "healthy dog", "https://www.lavanguardia.com/files/og_thumbnail/files/fp/uploads/2023/03/20/64184f2c49b88.r_d.640-427-0.jpeg");
            Pet pet2 = new Pet("Luna", "2 años", "Gato", "Persa", AnimalSize.BIGGER, "healthy cat", "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-06Persian20Long20Hair.2.jpg?itok=qzNV_HuV");
            Pet pet3 = new Pet("Pelusa", "3 meses", "Gato", "Siames", AnimalSize.MEDIUM, "angry cat, need sedation", "https://images.ctfassets.net/denf86kkcx7r/15HC92CcCjQEg7U14aZkVM/914db181ce7e407bc2a0220436a32aa4/un_gato_persa-39");
            Pet pet4 = new Pet("Coco", "1 año", "Perro", "Pastor Aleman", AnimalSize.SMALL, "blind dog", "https://www.purina.es/sites/default/files/styles/ttt_image_510/public/2024-02/sitesdefaultfilesstylessquare_medium_440x440public2022-07German20Shepherd20Dog1.jpg?itok=7Xg-W19h");
            Pet pet5 = new Pet("Tomate", "6 meses", "Perro", "Pastor Aleman", AnimalSize.LARGE, "healthy dog", "https://www.tiendanimal.es/articulos/wp-content/uploads/2017/11/Como-educar-y-entrenar-a-un-pastor-aleman.jpg");
            Pet pet6 = new Pet("Firulais", "15 años", "Perro", "Golden Retriever", AnimalSize.BIGGER, "very old dog", "https://www.clarin.com/2020/06/24/VXEcQ3tkG_720x0__1.jpg");

            lucas.addPet(pet2);
            pedro.addPet(pet3);
            leonel.addPet(pet4);

            petRepository.save(pet1);
            petRepository.save(pet2);
            petRepository.save(pet3);
            petRepository.save(pet4);
            petRepository.save(pet5);
            petRepository.save(pet6);

            List<TimeSlot> generalEnquirySlots = List.of(
                    new TimeSlot(Days.MONDAY, "09:00 AM"),
                    new TimeSlot(Days.MONDAY, "11:00 AM"),
                    new TimeSlot(Days.WEDNESDAY, "02:00 PM")
            );

            List<TimeSlot> vaccinationSlots = List.of(
                    new TimeSlot(Days.TUESDAY, "10:00 AM"),
                    new TimeSlot(Days.TUESDAY, "01:00 PM"),
                    new TimeSlot(Days.THURSDAY, "03:00 PM")
            );

            List<TimeSlot> surgerySlots = List.of(
                    new TimeSlot(Days.FRIDAY, "08:00 AM"),
                    new TimeSlot(Days.FRIDAY, "12:00 PM"),
                    new TimeSlot(Days.SATURDAY, "09:00 AM")
            );

            List<TimeSlot> canineHairdressingSlots = List.of(
                    new TimeSlot(Days.WEDNESDAY, "10:00 AM"),
                    new TimeSlot(Days.WEDNESDAY, "04:00 PM"),
                    new TimeSlot(Days.SATURDAY, "02:00 PM")
            );

            List<TimeSlot> dewormingSlots = List.of(
                    new TimeSlot(Days.THURSDAY, "09:00 AM"),
                    new TimeSlot(Days.THURSDAY, "11:00 AM"),
                    new TimeSlot(Days.SATURDAY, "03:00 PM")
            );
            // Instancias de Offering
            Offering generalEnquiry = new Offering(
                    "General Enquiry",
                    "General consultation for check-ups and medical examinations of your pets.",
                    50.00,
                    "https://img.freepik.com/free-photo/veterinarian-checking-dog-medium-shot_23-2149143871.jpg",
                    true,
                    generalEnquirySlots
            );

            Offering vaccination = new Offering(
                    "Vaccination",
                    "Complete vaccination to keep your pets protected against diseases.",
                    30.00,
                    "https://media.istockphoto.com/id/476916383/photo/microchip-implant.jpg?s=612x612&w=0&k=20&c=Y1QxVmAMIFjuNnbyHeyI06tXx7yK1ufxmZrFchwJdSg=",
                    true,
                    vaccinationSlots

            );

            Offering surgery = new Offering(
                    "Surgery",
                    "Surgeries and surgical procedures with the best care.",
                    200.00,
                    "https://t4.ftcdn.net/jpg/02/74/11/19/360_F_274111966_T8GKpkMq7RpgY2z1Iju1bVrFvdg5rp2A.jpg",
                    true,
                    surgerySlots
            );

            Offering canineHairdressing = new Offering(
                    "Canine Hairdressing",
                    "Grooming service to keep your pet clean and healthy.",
                    40.00,
                    "https://www.shutterstock.com/image-photo/happy-redhaired-ginger-woman-blowing-260nw-1513781225.jpg",
                    false,
                    canineHairdressingSlots
            );

            Offering deworming = new Offering(
                    "Deworming",
                    "Deworming service to eliminate internal and external parasites, keeping your pet healthy.",
                    25.00,
                    "https://st4.depositphotos.com/1144191/21299/i/450/depositphotos_212992002-stock-photo-reluctant-labrador-puppy-dog-getting.jpg",
                    true,
                    dewormingSlots
            );

            offeringRepository.save(generalEnquiry);
            offeringRepository.save(vaccination);
            offeringRepository.save(surgery);
            offeringRepository.save(canineHairdressing);
            offeringRepository.save(deworming);

            // creación de Appointment
            Appointment appointment1 = new Appointment(LocalDateTime.now().plusDays(3), LocalDateTime.now(), "Please get my dog a good shower, I´ll pick him up at noon", AppointmentStatus.CONFIRMED);

            // asignación de Cita a cliente
            appointment1.setClient(lucas);

            // asignación de cita a servicio
            appointment1.setOffering(generalEnquiry);

            // Creación de Veterinarios
//            Veterinarian vet1 = new Veterinarian("Brayan Ortiz", "Veterinary Dermatology", "Calle 1 # 2 - 3", "31334177", "derma@tology.com", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQNtxj8PUZH81_htfbowSspa_NzEy_P-4fylQ&s", true);
//
//            Veterinarian vet2 = new Veterinarian("Jason Ordoñez", "Animal Behavior", "Calle 4 # 5 - 6", "31334177", "behavior@tology.com", "https://img.freepik.com/foto-gratis/apuesto-joven-medico-clinica-veterinaria-apuntando-dedo-arriba-sonriendo-impresionado-pie-cerca-lindo-perro-pug-negro-fondo-blanco_1258-75927.jpg",   true);
//
//            Veterinarian vet3 = new Veterinarian("Orlando Carrascal", "Veterinary Anesthesiology and Emergency Medicine", "Calle 7 # 8 - 9", "31334177", "anesthesio@logy.com", "https://img.freepik.com/fotos-premium/joven-medico-veterinario-masculino-confiado-uniforme-sentado-lugar-trabajo_236854-46856.jpg",  true);
//
//            Veterinarian vet4 = new Veterinarian("Marcela Osorio", "Intern", "Calle 10 # 11 - 12", "31334177", "intern@tology.com", "https://img.freepik.com/fotos-premium/mujer-veterinaria-uniforme-clinica-abrazando-perro-amor_255667-38056.jpg", true);

            Veterinarian vet1 = new Veterinarian("Brayan",
                    "Ortiz",
                    "Veterinary Dermatology",
                    "Calle 1 # 2 - 3",
                    "31334177",
                    "derma@tology.com",
                    passwordEncoder.encode("abc123"),
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQNtxj8PUZH81_htfbowSspa_NzEy_P-4fylQ&s",
                    true);
            // Asignación de Cita a veterinario
            vet1.addAppointment(appointment1);

            veterinarianRepository.save(vet1);
//            veterinarianRepository.save(vet2);
//            veterinarianRepository.save(vet3);
//            veterinarianRepository.save(vet4);

            // Creación de Invoice
            Invoice invoice1 = new Invoice(LocalDateTime.now(), 1000, InvoiceStatus.CHARGED);

            // Asignar invoice a la cita
            invoice1.setAppointment(appointment1);
            appointment1.setInvoice(invoice1);
            invoice1.setAccount(account2);

            invoiceRepository.save(invoice1);

            // Asignación de cita al cliente
            lucas.addAppointment(appointment1);

            // asignar cuenta a la invoice
            invoice1.setAccount(account4);

            // Asignar pet a la cita
            pet1.addAppointment(appointment1);

            // Asignar cita al veterinario
//            vet2.addAppointment(appointment1);

            appointmentRepository.save(appointment1);

            // para saber cuando inicia la app
            System.out.println("****************************************************");
            System.out.println("*                                                  *");
            System.out.println("*        Bienvenido a Mi Aplicación Veterinaria    *");
            System.out.println("*                                                  *");
            System.out.println("****************************************************");

        };
    }

//    public static List<String> generateNextSevenDays() {
//        List<String> dates = new ArrayList<>();
//        LocalDate currentDate = LocalDate.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//        int addedDays = 0;
//        while (addedDays < 7) {
//            if (!(currentDate.getDayOfWeek() == DayOfWeek.SATURDAY || currentDate.getDayOfWeek() == DayOfWeek.SUNDAY)) {
//                dates.add(currentDate.format(formatter));
//                addedDays++;
//            }
//            currentDate = currentDate.plusDays(1);
//        }
//
//        return dates;
//    }
}