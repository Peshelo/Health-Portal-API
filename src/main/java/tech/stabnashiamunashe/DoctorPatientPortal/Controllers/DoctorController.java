package tech.stabnashiamunashe.DoctorPatientPortal.Controllers;

import tech.stabnashiamunashe.DoctorPatientPortal.Models.Doctors;
import tech.stabnashiamunashe.DoctorPatientPortal.Services.DoctorService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/v1/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }


    @GetMapping()
    public List<Doctors> getAllDoctors(){
        return doctorService.getAllDoctors();
    }

    @GetMapping("/get_by_profession")
    public List<Doctors> getDoctorsBySpecialization(String specialization){
        return doctorService.getDoctorsBySpecialization(specialization);
    }
    @GetMapping("/{offset}/{size}")
    public Page<Doctors> getPagedDoctorsByPageNumberAndPageSize(@PathVariable int offset,
                                                @PathVariable int size)
    {
        return doctorService.getDoctorsPagedAndSized(offset, size);
    }

    @GetMapping("/{size}")
    public Page<Doctors> getPagedDoctorsByPageSize(@PathVariable int size)
    {
        return doctorService.getDoctorsPaged(size);
    }


    @PostMapping("/register")
    public String registerDoctor(@RequestBody Doctors doctor){
        return doctorService.saveDoctor(doctor);
    }


    @DeleteMapping("delete{id}")
    public String deleteDoctor(@RequestBody Long id){
        return doctorService.deleteDoctorById(id);
    }

    @PutMapping("/update/{id}")
    public Doctors updateDoctor(@PathVariable Long id, @RequestBody Doctors doctor){
        return doctorService.updateDoctor(id , doctor);
    }

    @GetMapping(produces = "application/json")
    public Doctors getDoctorDetails(Principal principal){
        return doctorService.getDoctorDetails(principal.getName());
    }
}
