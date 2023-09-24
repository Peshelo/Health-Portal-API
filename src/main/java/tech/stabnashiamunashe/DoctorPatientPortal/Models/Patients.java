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
public class Patients extends User {

    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    private List<Appointments> appointments;

    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    private List<PatientRecords> patientRecords;

}
