package tech.stabnashiamunashe.DoctorPatientPortal.Repositories;

import tech.stabnashiamunashe.DoctorPatientPortal.Models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
