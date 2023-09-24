package tech.stabnashiamunashe.DoctorPatientPortal.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import tech.stabnashiamunashe.DoctorPatientPortal.Security.Models.User;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Doctors extends User {

     @OneToMany(mappedBy = "doctor")
     @JsonIgnore
     private List<Appointments> appointments;

      private String specialisation;

      private Double rating;
}
