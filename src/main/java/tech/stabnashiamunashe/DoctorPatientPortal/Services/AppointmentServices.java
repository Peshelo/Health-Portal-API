package tech.stabnashiamunashe.DoctorPatientPortal.Services;

import tech.stabnashiamunashe.DoctorPatientPortal.Models.AppointmentStatus;
import tech.stabnashiamunashe.DoctorPatientPortal.Models.Appointments;
import tech.stabnashiamunashe.DoctorPatientPortal.Repositories.AppointmentRepository;
import tech.stabnashiamunashe.DoctorPatientPortal.Repositories.DoctorRepository;
import tech.stabnashiamunashe.DoctorPatientPortal.Repositories.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentServices {

    private final AppointmentRepository appointmentRepository;

    private final PatientRepository patientRepository;

    private final DoctorRepository doctorRepository;

    public AppointmentServices(AppointmentRepository appointmentRepository, PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

        public boolean isSlotNotFree(LocalDateTime start, Long docId) {
        return appointmentRepository.existsByStartAndId(start, docId);
    }

    public String createNewAppointment(Long doctorId, Long patientId, LocalDateTime start) {

        if(appointmentRepository.existsByStartAndDoctorId(start, doctorId)){
            return "Appointment already Booked!";
        }
        Appointments appointment = new Appointments();
        appointment.setStatus(AppointmentStatus.SCHEDULED);
        patientRepository.findById(patientId).ifPresent(appointment::setPatient);
        doctorRepository.findById(doctorId).ifPresent(appointment::setDoctor);
        appointment.setStart(start);
        appointment.setEnd(start.plusMinutes(30));
        appointmentRepository.save(appointment);
        return "Appointment Created";
    }

    public List<Appointments> findByPatientsAppointmentsById(Long id) {
        return appointmentRepository.findAllByPatientId(id);
    }

    public List<Appointments> findBookedDoctorAppointmentsById(Long id) {
        return appointmentRepository.findAllByDoctorId(id);
    }

    public String deleteAppointmentById(Long id) {
        appointmentRepository.deleteById(id);
        return "Appointment Successfully Deleted!";
    }

    public String MarkAsDone(Long id) {
        Appointments appointments = appointmentRepository.findById(id).orElse(null);
        if(appointments != null){
            appointments.setStatus(AppointmentStatus.FINISHED);
            appointmentRepository.save(appointments);
            return "Appointment marked as Done!";
        }
        return "Appointment Not Found!";
    }
}
