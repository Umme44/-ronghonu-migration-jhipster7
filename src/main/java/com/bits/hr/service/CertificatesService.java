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

        // Get certificate by ID
        public Optional<Certificates> getCertificateById(Long id) {
            return certificatesRepository.findById(id);
        }

        //find BY id
        public Optional<Certificates> findById(Long id) {
            return certificatesRepository.findById(id);
        }

        //create new certificate

        public Certificates saveCertificate(Certificates certificate) {
            return certificatesRepository.save(certificate);
        }

        // Delete a certificate by ID
        public void deleteCertificate(Long id) {
            certificatesRepository.deleteById(id);
        }

        // Update an existing certificate by ID
        public Certificates updateCertificate(Long id, Certificates updatedCertificate) {
            return certificatesRepository.findById(id).map(certificate -> {
                certificate.setPin(updatedCertificate.getPin());
                certificate.setImageUrl(updatedCertificate.getImageUrl());
                certificate.setDescription(updatedCertificate.getDescription());
                certificate.setMaterialsLearned(updatedCertificate.getMaterialsLearned());
                certificate.setEnrollmentDate(updatedCertificate.getEnrollmentDate());
                certificate.setCompletionDate(updatedCertificate.getCompletionDate());
                return certificatesRepository.save(certificate);
            }).orElse(null);
        }

        public List<Certificates> findAllCertificates() {
            return certificatesRepository.findAll();
        }






















}


