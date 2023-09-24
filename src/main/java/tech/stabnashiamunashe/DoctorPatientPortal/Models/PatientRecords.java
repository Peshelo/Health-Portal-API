package tech.stabnashiamunashe.DoctorPatientPortal.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PatientRecords {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String recordDetail;

    @OneToMany(mappedBy = "patientRecords")
    private Set<Documents> documents;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dateOfEntry;

    @ManyToOne
    @JoinColumn(name = "id_patient")
    private Patients patient;

    public PatientRecords(String recordDetail, LocalDateTime dateOfEntry, Patients patient) {
        this.recordDetail = recordDetail;
        this.dateOfEntry = dateOfEntry;
        this.patient = patient;
    }
}
