package tech.stabnashiamunashe.DoctorPatientPortal.Services;

import tech.stabnashiamunashe.DoctorPatientPortal.Models.Doctors;
import tech.stabnashiamunashe.DoctorPatientPortal.Repositories.DoctorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    private final PasswordEncoder passwordEncoder;

    public DoctorService(DoctorRepository doctorRepository, PasswordEncoder passwordEncoder) {
        this.doctorRepository = doctorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Doctors> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public String deleteDoctorById(Long id) {
        try{
            doctorRepository.deleteById(id);
            return "Successfully Deleted Doctor";
        }catch(Exception e){
            return e.getMessage();
        }
    }

    public String saveDoctor(Doctors doctor) {
        if(doctorRepository.existsByEmail(doctor.getEmail())){
            return "Doctor Already Exists!";
        }
        doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
        doctor.setRoles("DOCTOR");
        doctorRepository.save(doctor);
        return "Doctor Saved!";
    }

    public Doctors updateDoctor(Long id, Doctors newDoctor) {
        Doctors doctor = doctorRepository.findById(id).orElse(null);
        if(doctor != null){
            doctor.setSpecialisation(newDoctor.getSpecialisation());
            doctor.setPassword(passwordEncoder.encode(newDoctor.getPassword()));
            doctor.setFirstname(newDoctor.getFirstname());
            doctor.setMobile(newDoctor.getMobile());
            doctor.setLastname(newDoctor.getLastname());
            doctor.setAddress(newDoctor.getAddress());
            return doctorRepository.save(doctor);
        }
        else return null;

    }

    public List<Doctors> getDoctorsBySpecialization(String specialization) {
        return doctorRepository.findAllBySpecialisation(specialization);
    }

    public Page<Doctors> getDoctorsPagedAndSized(int offset, int size) {
        return doctorRepository.findAll(PageRequest.of(offset,size));
    }

    public List<Doctors> getDoctorsBySpecializationSortedByRating(String specialization) {
        List<Doctors> allspecialists =  doctorRepository.findAllBySpecialisation(specialization, Sort.by(Sort.Direction.DESC, "rating"));
        return doctorRepository.findAllBySpecialisation(specialization);
    }

    public Page<Doctors> getDoctorsPaged(int size) {
        return doctorRepository.findAll(Pageable.ofSize(size));
    }

    public List<Doctors> getAllDoctorsSortedByRating() {
        return doctorRepository.findAll(Sort.by("rating").descending());
    }

    public Doctors getDoctorDetails(String email) {
        return doctorRepository.findByEmail(email);
    }
}
