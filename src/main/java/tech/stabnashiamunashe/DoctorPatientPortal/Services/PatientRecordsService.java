package tech.stabnashiamunashe.DoctorPatientPortal.Services;

import tech.stabnashiamunashe.DoctorPatientPortal.Models.PatientRecords;
import tech.stabnashiamunashe.DoctorPatientPortal.Repositories.PatientRecordsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientRecordsService {

    private final PatientRecordsRepository patientRecordsRepository;

    public PatientRecordsService(PatientRecordsRepository patientRecordsRepository) {
        this.patientRecordsRepository = patientRecordsRepository;
    }


    public PatientRecords addRecord(PatientRecords patientRecords) {
        return patientRecordsRepository.save(patientRecords);
    }

    public List<PatientRecords> findAll() {
        return patientRecordsRepository.findAll();
    }


    public Page<PatientRecords> getPatientRecordsWithPagenation(int offset, int size){
        return patientRecordsRepository.findAll(PageRequest.of(offset, size));
    }

    public List<PatientRecords> getSortedPatientRecords(){
        return patientRecordsRepository.findAll(Sort.by(Sort.Direction.DESC , "dateOfEntry"));
    }

    public PatientRecords findById(Long patientRecordId) {
        return patientRecordsRepository.findById(patientRecordId).orElse(null);
    }

    public List<PatientRecords> findAllById(Long id) {
        return patientRecordsRepository.findAllById(id);
    }
}
