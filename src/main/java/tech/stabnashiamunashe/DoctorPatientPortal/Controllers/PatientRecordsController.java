package tech.stabnashiamunashe.DoctorPatientPortal.Controllers;

import tech.stabnashiamunashe.DoctorPatientPortal.Emails.EmailService;
import tech.stabnashiamunashe.DoctorPatientPortal.Models.Documents;
import tech.stabnashiamunashe.DoctorPatientPortal.Models.PatientRecords;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.stabnashiamunashe.DoctorPatientPortal.Services.AzureBlobService;
import tech.stabnashiamunashe.DoctorPatientPortal.Services.DocumentsService;
import tech.stabnashiamunashe.DoctorPatientPortal.Services.PatientRecordsService;
import tech.stabnashiamunashe.DoctorPatientPortal.Services.PatientService;

import java.io.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/v1/records")
public class PatientRecordsController {

    private final PatientRecordsService patientRecordsService;

    private final DocumentsService documentsService;

    private final AzureBlobService azureBlobService;

    private final PatientService patientService;

    private final EmailService emailService;

    public PatientRecordsController(PatientRecordsService patientRecordsService, DocumentsService documentsService, AzureBlobService azureBlobService, PatientService patientService, EmailService emailService) {
        this.patientRecordsService = patientRecordsService;
        this.documentsService = documentsService;
        this.azureBlobService = azureBlobService;
        this.patientService = patientService;
        this.emailService = emailService;
    }

//    @PostMapping("/upload")
//    public String uploadDocument(@RequestParam MultipartFile file) throws IOException {
//        azureBlobService.storeFile(file.getOriginalFilename(),file.getInputStream(), file.getSize());
//        return "File Uploaded";
//    }


    @GetMapping("/download/{url}")
    public ResponseEntity<?> downloadImageByUrl(@PathVariable String url){
        return azureBlobService.download(url);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam String recordDetails,
                            @RequestParam Long patientId ,
                            @RequestParam MultipartFile file) {

        try {
            String savedName;
            UUID uuid = UUID.randomUUID();
            savedName = uuid.toString();
            azureBlobService.uploadFile(file, savedName);
            PatientRecords patientRecords = new PatientRecords(recordDetails, LocalDateTime.now(), patientService.getPatientById(patientId));
            PatientRecords savePatientRecord = patientRecordsService.addRecord(patientRecords);
            Documents documents = new Documents(file.getOriginalFilename(), savedName , patientRecordsService.findById(savePatientRecord.getId()));
            documentsService.saveDocument(documents);
            String Message = "Doctor {} has uploaded your documents for " + recordDetails + ". Log into your portal to view the Record!";
            emailService.sendEmail(patientService.getPatientById(patientId).getEmail(),"Uploaded Records", Message );
            return ResponseEntity.status(HttpStatus.CREATED).body("Document Uploaded!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file.");
        }
    }

    @GetMapping("/{offset}/{size}")
    public Page<PatientRecords> getPagedRecords(@PathVariable int offset,
                                                @PathVariable int size)
    {
        return patientRecordsService.getPatientRecordsWithPagenation(offset, size);
    }

    @GetMapping("all/{id}")
    public List<PatientRecords> getAllRecordsForPatient(@PathVariable Long id){
        return patientRecordsService.findAllById(id);
    }
    @GetMapping("/sorted_by_recent")
    public List<PatientRecords> getRecordsByMostRecent()
    {
        return patientRecordsService.getSortedPatientRecords();
    }

    @GetMapping("/doctor/all")
    public List<PatientRecords> getAllRecords(){
        return patientRecordsService.findAll();
    }

}
