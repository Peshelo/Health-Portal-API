package tech.stabnashiamunashe.DoctorPatientPortal.Repositories;

import tech.stabnashiamunashe.DoctorPatientPortal.Models.Appointments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointments, Long> {

    boolean existsByStartAndId(LocalDateTime start, Long id);

    List<Appointments> findAllByPatientId(Long id);

    boolean existsByStartAndDoctorId(LocalDateTime start, Long id);

    List<Appointments> findAllByDoctorId(Long id);
}
