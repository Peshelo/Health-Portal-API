package tech.stabnashiamunashe.DoctorPatientPortal.Repositories;

import tech.stabnashiamunashe.DoctorPatientPortal.Models.PatientRecords;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRecordsRepository extends JpaRepository<PatientRecords, Long> {

    List<PatientRecords> findAllById(Long id);
}
