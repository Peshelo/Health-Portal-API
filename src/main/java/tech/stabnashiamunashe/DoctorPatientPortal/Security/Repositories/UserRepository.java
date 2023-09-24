package tech.stabnashiamunashe.DoctorPatientPortal.Security.Repositories;

import tech.stabnashiamunashe.DoctorPatientPortal.Security.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
