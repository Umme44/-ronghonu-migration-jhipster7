package com.bits.hr.service;


import com.bits.hr.domain.Certificates;
import com.bits.hr.repository.CertificatesRepository;
import com.bits.hr.service.dto.CertificatesDTO;
import com.bits.hr.service.mapper.CertificatesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CertificatesService{

        @Autowired
        private CertificatesRepository certificatesRepository;

        @Autowired
        private CertificatesMapper certificatesMapper;



        public CertificatesDTO createCertificate(CertificatesDTO certificatesDTO) {
            Certificates certificate = certificatesMapper.toEntity(certificatesDTO);
             certificatesRepository.save(certificate);
            return certificatesDTO;
        }



        public CertificatesDTO getCertificateById(Long id) {
            Certificates certificate = certificatesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Certificate not found"));
            return certificatesMapper.toDto(certificate);
        }

        public List<CertificatesDTO> getAllCertificates() {
            List<Certificates> certificates = certificatesRepository.findAll();
            return List.of();
        }


        public CertificatesDTO deleteCertificateById(Long id) {
            Optional<Certificates> certificate = certificatesRepository.findById(id);
            return null;
        }

        /*public CertificatesDTO updateCertificate(CertificatesDTO certificatesDTO) {
            Long id = certificatesDTO.getId();
            Optional<Certificates> certificateOptional = certificatesRepository.findById(id);

            if (certificateOptional.isPresent()) {
                // If the certificate exists, convert the DTO to an entity
                Certificates certificate = certificatesMapper.toEntity(certificatesDTO);
                // You can also manually set other fields if needed, for example:
                // certificate.setIssueDate(certificateOptional.get().getIssueDate()); // if you want to keep the existing issue date

                // Save the updated certificate back to the database
                certificatesRepository.save(certificate);

                // Convert the updated entity back to DTO and return it
                return certificatesMapper.toDto(certificate);
            } else {
                // Handle the case where the certificate does not exist
                throw new EntityNotFoundException("Certificate not found with ID: " + id);
            }
        }*/


    public CertificatesDTO updateCertificate(CertificatesDTO certificatesDTO) {
        Long id = certificatesDTO.getId();
        Optional<Certificates> certificateOptional = certificatesRepository.findById(id);

        if (certificateOptional.isPresent()) {
            // Convert the DTO to an entity
            Certificates certificate = certificatesMapper.toEntity(certificatesDTO);
            // Set additional fields if needed (optional)
            // certificate.setIssueDate(certificateOptional.get().getIssueDate()); // Example

            // Save the updated certificate back to the database
            Certificates updatedCertificate = certificatesRepository.save(certificate);

            // Convert the updated entity back to DTO and return it
            return certificatesMapper.toDto(updatedCertificate);
        } else {
            // Handle the case where the certificate does not exist
            throw new EntityNotFoundException("Certificate not found with ID: " + id);
        }
    }

}


