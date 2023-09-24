package tech.stabnashiamunashe.DoctorPatientPortal.Services;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobHttpHeaders;
import com.azure.storage.blob.models.BlobItem;
import com.azure.storage.blob.models.BlobProperties;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AzureBlobService {

    private final AzureBlobProperties azureBlobProperties;

    public AzureBlobService(AzureBlobProperties azureBlobProperties) {
        this.azureBlobProperties = azureBlobProperties;
    }

    private BlobContainerClient containerClient() {
        BlobServiceClient serviceClient = new BlobServiceClientBuilder()
                .connectionString(azureBlobProperties.getConnectionstring()).buildClient();
        return serviceClient.getBlobContainerClient(azureBlobProperties.getContainer());
    }

    public ResponseEntity<?> download(String imageIdentifier) {

        BlobContainerClient containerClient = containerClient();
        BlobClient blobClient = containerClient.getBlobClient(imageIdentifier);

        BlobProperties properties = blobClient.getProperties();
        String contentType = properties.getContentType();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        blobClient.download(outputStream);
        byte[] content = outputStream.toByteArray();
        return  ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.parseMediaType(contentType))
                .body(content);
    }


    public void uploadFile(MultipartFile file, String name) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

//        try {
//            File resizedFile = File.createTempFile("resized-", "-" + file.getOriginalFilename());
//            file.transferTo(resizedFile);
//
//            Thumbnails.of(resizedFile)
//                    .size(750, 750) // resize image to 750x750
//                    .outputQuality(0.75) // set image quality to 75%
//                    .toOutputStream(outputStream); // write the output to the ByteArrayOutputStream
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        }

        byte[] imageBytes = outputStream.toByteArray();

        // upload the compressed and resized image to Azure Blob Storage
        String contentType = file.getContentType();
        BlobContainerClient containerClient = containerClient();
        BlobClient blobClient = containerClient.getBlobClient(name);
        blobClient.upload(new ByteArrayInputStream(imageBytes), imageBytes.length, true);
        BlobHttpHeaders headers = new BlobHttpHeaders().setContentType(contentType);
        blobClient.setHttpHeaders(headers);
    }

}
