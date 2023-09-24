package tech.stabnashiamunashe.DoctorPatientPortal.Security.Controllers;


import tech.stabnashiamunashe.DoctorPatientPortal.Security.Service.TokenService;
import tech.stabnashiamunashe.DoctorPatientPortal.Models.Doctors;
import tech.stabnashiamunashe.DoctorPatientPortal.Models.Patients;
import tech.stabnashiamunashe.DoctorPatientPortal.Services.DoctorService;
import tech.stabnashiamunashe.DoctorPatientPortal.Services.PatientService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    private final DoctorService doctorService;

    private final PatientService patientService;
    private final TokenService tokenService;

    private final AuthenticationManager authenticationManager;

    public AuthController(DoctorService doctorService, PatientService patientService, TokenService tokenService, AuthenticationManager authenticationManager) {
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public String registerPatient(@RequestBody Patients patient) {
        return patientService.savePatient(patient);
    }

    //FOR TEST ONLY , DELETE THIS LATER!!!!
    @PostMapping("/register-doc")
    public String registerDoc(@RequestBody Doctors doctor) {
        return doctorService.saveDoctor(doctor);
    }

    @PostMapping("/login")
    public String token(@RequestBody LoginRequest loginRequest){

        try {

            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password()));
            System.out.println(authentication.getAuthorities());
            return "token : " + tokenService.generateToken(authentication) + "\n role : " + authentication.getAuthorities();

        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return "User Not Found";
        }
    }
}
