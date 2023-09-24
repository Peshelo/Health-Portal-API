package tech.stabnashiamunashe.DoctorPatientPortal.Repositories;

import tech.stabnashiamunashe.DoctorPatientPortal.Models.Patients;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patients, Long> {

    Patients findByEmail(String email);

    Optional<Patients> findById(Long id);

    boolean existsByEmail(String email);
}
