package tech.stabnashiamunashe.DoctorPatientPortal.Services;

import tech.stabnashiamunashe.DoctorPatientPortal.Models.Documents;
import tech.stabnashiamunashe.DoctorPatientPortal.Repositories.DocumentsRepository;
import org.springframework.stereotype.Service;

@Service
public class DocumentsService {

    private final DocumentsRepository documentsRepository;

    public DocumentsService(DocumentsRepository documentsRepository) {
        this.documentsRepository = documentsRepository;
    }

    public void saveDocument(Documents documents) {
        documentsRepository.save(documents);
    }
}
