package tech.stabnashiamunashe.DoctorPatientPortal.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Documents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String documentName;

    private String documentURL;

    @ManyToOne
    @JoinColumn(name="id_patient_records", nullable=false)
    private PatientRecords patientRecords;

    public Documents(String documentName, String documentURL, PatientRecords patientRecords) {
        this.documentName = documentName;
        this.documentURL = documentURL;
        this.patientRecords = patientRecords;
    }
}
