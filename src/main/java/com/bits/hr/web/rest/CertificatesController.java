package com.bits.hr.web.rest;


import com.bits.hr.domain.Certificates;
import com.bits.hr.repository.CertificatesRepository;
import com.bits.hr.service.CertificatesService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee-mgt/certificate")
public class CertificatesController {

    @Autowired
    private CertificatesService certificatesService;

    @Autowired
    private CertificatesRepository certificatesRepository;

    // Get all employee Certificate
    @GetMapping()
    public List<Certificates> getAllCertificate(){
        return certificatesRepository.findAll();

    }

    //Get certificate by id
    @GetMapping("/{id}")
    public ResponseEntity<Certificates> getCertificateById(@PathVariable Long id) {
        Optional<Certificates> certificate = certificatesService.findById(id);
        return certificate.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new certificate
    @PostMapping()
    public Certificates createCertificate(@RequestBody Certificates certificate) {
        return certificatesService.saveCertificate(certificate);
    }


    // Delete a certificate by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCertificate(@PathVariable Long id) {
        certificatesService.deleteCertificate(id);
        return ResponseEntity.noContent().build();
    }

    // Update an existing certificate by ID
    @PutMapping("/{id}")
    public ResponseEntity<Certificates> updateCertificate(
        @PathVariable Long id, @RequestBody Certificates updatedCertificate) {
        Certificates certificate = certificatesService.updateCertificate(id, updatedCertificate);
        return certificate != null ? ResponseEntity.ok(certificate) : ResponseEntity.notFound().build();
    }
















}

