package tech.stabnashiamunashe.DoctorPatientPortal.Controllers;

import tech.stabnashiamunashe.DoctorPatientPortal.Models.Appointments;
import tech.stabnashiamunashe.DoctorPatientPortal.Models.Patients;
import tech.stabnashiamunashe.DoctorPatientPortal.Services.AppointmentServices;
import tech.stabnashiamunashe.DoctorPatientPortal.Services.PatientService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("v1/appointments")
public class AppointmentController {

    private final AppointmentServices appointmentServices;

    private final PatientService patientService;

    public AppointmentController(AppointmentServices appointmentServices, PatientService patientService) {
        this.appointmentServices = appointmentServices;
        this.patientService = patientService;
    }

    @PostMapping("/createAppointment")
    public @ResponseBody String createAppointment(@RequestParam Long doctorId, @RequestParam String start, Authentication authentication){

        if(appointmentServices.isSlotNotFree(LocalDateTime.parse(start), doctorId)){
            return "Appointment Slot already booked!";
        }

        Long patientId = patientService.getPatientByEmail(authentication.getName()).getId();

        return appointmentServices.createNewAppointment(doctorId, patientId, LocalDateTime.parse(start));

    }

    @GetMapping(value = "/get_appointments_for_patient")
    public List<Appointments> getAppointments(Principal principal){
        Patients patient = patientService.getPatientByUsername(principal.getName());
        return appointmentServices.findByPatientsAppointmentsById(patient.getId());
    }

    @GetMapping("/get_doctor_appointments")
    @PreAuthorize("hasAuthority('SCOPE_PATIENT')")
    public List<Appointments> findByPatientsAppointmentsById(Long id) {
        return appointmentServices.findBookedDoctorAppointmentsById(id);
    }

    @DeleteMapping("/delete/{id}")
    public  String deleteAppointmentById(@PathVariable Long id){
        return appointmentServices.deleteAppointmentById(id);
    }

    @PutMapping("/mark_done_appointment")
    public String markAppointmentAsDone(Long id){
        return appointmentServices.MarkAsDone(id);
    }
}
