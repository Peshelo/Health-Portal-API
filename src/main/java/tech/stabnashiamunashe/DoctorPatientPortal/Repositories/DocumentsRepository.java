package tech.stabnashiamunashe.DoctorPatientPortal.Repositories;

import tech.stabnashiamunashe.DoctorPatientPortal.Models.Documents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentsRepository extends JpaRepository<Documents, Long> {
}
