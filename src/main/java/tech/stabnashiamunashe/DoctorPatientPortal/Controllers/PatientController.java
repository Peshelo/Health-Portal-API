package tech.stabnashiamunashe.DoctorPatientPortal.Controllers;

import tech.stabnashiamunashe.DoctorPatientPortal.Models.Patients;
import tech.stabnashiamunashe.DoctorPatientPortal.Services.PatientService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/v1/patients")
public class PatientController {


    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping(value = "/all" , produces = "application/json")
    @PreAuthorize("hasAuthority('SCOPE_PATIENT')")
    public List<Patients> getAllPatients(Principal principal){
        return patientService.getAllPatients();
    }

    @DeleteMapping("delete{id}")
    public String deletePatient(@PathVariable Long id){
        return patientService.deletePatientById(id);
    }

    @PutMapping("/update/{id}")
    public Patients updatePatient(@PathVariable Long id, @RequestBody Patients patient){
        return patientService.updatePatient(id, patient);
    }

    @GetMapping(produces = "application/json")
    public Patients getPatientDetail(Principal principal){
        return patientService.getPatientDetails(principal.getName());
    }
}
