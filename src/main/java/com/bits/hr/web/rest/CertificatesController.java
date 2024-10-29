package com.bits.hr.web.rest;


import com.bits.hr.domain.Certificates;
import com.bits.hr.repository.CertificatesRepository;
import com.bits.hr.service.CertificatesService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee-mgt")
public class CertificatesController {

    @Autowired
    private CertificatesService certificatesService;

    @Autowired
    private CertificatesRepository certificatesRepository;

    // Get all employee Certificate
    @GetMapping("/certificates")
    public List<Certificates> getAllCertificates() {
        return certificatesService.getAllCertificates();
    }

    //Get certificate by id
    @GetMapping("/certificates/{id}")
    public ResponseEntity<Certificates> getCertificateById(@PathVariable Long id) {
        Certificates certificates = certificatesService.getCertificateById(id);
        return ResponseEntity.ok(certificates);
    }


    // Create a new certificate
    @PostMapping("/create_certificates")
    public ResponseEntity<Certificates> createCertificate(@RequestBody Certificates certificate) {
        Certificates createdCertificate = certificatesService.createCertificate(certificate);
        return ResponseEntity.status(201).body(createdCertificate);
    }


    // Delete a certificate by ID
    @DeleteMapping("/certificates/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCertificate(@PathVariable Long id) {
        certificatesService.deleteCertificate(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }



    @PutMapping("/certificates/{id}")
    public ResponseEntity<Certificates> updateCertificates(@PathVariable Long id, @RequestBody Certificates certificatesDetails) {
        Certificates updatedCertificate = certificatesService.updateCertificates(id, certificatesDetails);
        return ResponseEntity.ok(updatedCertificate);
    }
















}

