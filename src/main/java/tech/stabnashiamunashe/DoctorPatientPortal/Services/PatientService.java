package tech.stabnashiamunashe.DoctorPatientPortal.Services;

import tech.stabnashiamunashe.DoctorPatientPortal.Models.Patients;
import tech.stabnashiamunashe.DoctorPatientPortal.Repositories.PatientRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    private final PasswordEncoder passwordEncoder;

    public PatientService(PatientRepository patientRepository, PasswordEncoder passwordEncoder) {
        this.patientRepository = patientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Patients> getAllPatients() {
        return patientRepository.findAll();
    }

    public String deletePatientById(Long id) {
        try{
            patientRepository.deleteById(id);
            return "Successfully Deleted Patient";
        }catch(Exception e){
            return e.getMessage();
        }
    }

    public String savePatient(Patients patient) {
        if(patientRepository.existsByEmail(patient.getEmail())){
            return "Patient Already Exists!";
        }
        patient.setPassword(passwordEncoder.encode(patient.getPassword()));
        patient.setRoles("PATIENT");
        patientRepository.save(patient);
        return "Patient Saved!";
    }

    public Patients getPatientByEmail(String email) {
        return patientRepository.findByEmail(email);
    }

    public Patients getPatientByUsername(String email) {
        return patientRepository.findByEmail(email);
    }

    public Patients getPatientById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    public Patients updatePatient(Long id, Patients newPatient) {
        Patients patient = patientRepository.findById(id).orElse(null);
        if(patient != null){
            patient.setPassword(passwordEncoder.encode(newPatient.getPassword()));
            patient.setFirstname(newPatient.getFirstname());
            patient.setMobile(newPatient.getMobile());
            patient.setLastname(newPatient.getLastname());
            patient.setAddress(newPatient.getAddress());
            return patientRepository.save(patient);
        }
        else return null;

    }

    public Patients getPatientDetails(String email) {
        return patientRepository.findByEmail(email);
    }
}
