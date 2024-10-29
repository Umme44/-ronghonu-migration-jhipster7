package com.bits.hr.service;


//import com.bits.hr.domain.Certificates;
import com.bits.hr.repository.CertificatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bits.hr.domain.Certificates;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CertificatesService{

        @Autowired
        private CertificatesRepository certificatesRepository;


//        @Autowired
//        private  ImageService imageService;

        //get all certificates
        public List<Certificates> getAllCertificates() {
            return certificatesRepository.findAll();
        }

        // get certificates by id
        public Certificates getCertificateById(Long id) {
            return certificatesRepository.findById(id)
                .orElseThrow();
        }

        //create new certificates
        public Certificates createCertificate(Certificates certificate) {
            // You can add any additional business logic here if needed
            return certificatesRepository.save(certificate);
        }

        //Delete certificates
        public void deleteCertificate(Long id) {
            Certificates certificates = certificatesRepository.findById(id)
                .orElseThrow();
            certificatesRepository.delete(certificates);
        }

        //Update certificates
        public Certificates updateCertificates(Long id, Certificates certificatesDetails) {
            Certificates existingCertificate = certificatesRepository.findById(id)
                .orElseThrow();

            // Update fields
            existingCertificate.setDescription(certificatesDetails.getDescription());
            existingCertificate.setCompletionDate(certificatesDetails.getCompletionDate());
            existingCertificate.setEnrollmentDate(certificatesDetails.getEnrollmentDate());
            existingCertificate.setImageUrl(certificatesDetails.getImageUrl());
            existingCertificate.setPin(certificatesDetails.getPin());
            existingCertificate.setExpired(certificatesDetails.getExpired());
            existingCertificate.setExpirationDate(certificatesDetails.getExpirationDate());

            return certificatesRepository.save(existingCertificate);
        }


}



