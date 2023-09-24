package tech.stabnashiamunashe.DoctorPatientPortal.Models;

import tech.stabnashiamunashe.DoctorPatientPortal.Security.Models.User;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Admin extends User {


}
