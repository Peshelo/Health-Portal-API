package tech.stabnashiamunashe.DoctorPatientPortal.Repositories;

import tech.stabnashiamunashe.DoctorPatientPortal.Models.Doctors;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctors, Long> {

    Doctors findByEmail(String email);

    boolean existsByEmail(String email);

    List<Doctors> findAllBySpecialisation(String specialization);

    List<Doctors> findAllBySpecialisation(String specialization, Sort sort);

}
