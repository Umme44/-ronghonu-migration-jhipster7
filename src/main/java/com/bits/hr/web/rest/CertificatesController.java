package com.bits.hr.web.rest;


import com.bits.hr.domain.Certificates;
import com.bits.hr.repository.CertificatesRepository;
import com.bits.hr.service.CertificatesService;

import com.bits.hr.service.dto.CertificatesDTO;
import com.bits.hr.service.mapper.CertificatesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employee-mgt/certificates/")
public class CertificatesController {

    @Autowired
    private final CertificatesService certificatesService;
    @Autowired
    private CertificatesRepository certificatesRepository;
    @Autowired
    private CertificatesMapper certificatesMapper;


    public CertificatesController(CertificatesService certificatesService) {
        this.certificatesService = certificatesService;
    }

    @PostMapping
    public ResponseEntity<CertificatesDTO> createOrUpdateCertificate(@RequestBody CertificatesDTO certificatesDTO) {
        CertificatesDTO savedCertificate = certificatesService.createCertificate(certificatesDTO);
        return ResponseEntity.status(savedCertificate.getId() == null ? HttpStatus.CREATED : HttpStatus.OK)
            .body(savedCertificate);
    }

    // READ Single Certificate by ID
    @GetMapping("/api/attendance-mgt/{id}")
    public CertificatesDTO getCertificateById(@PathVariable Long id) {
        return certificatesService.getCertificateById(id);
    }

    // READ All Certificates
    /*@GetMapping
    public List<Certificates> getAllCertificates() {
        return certificatesService.getAllCertificates();
    }*/


    @GetMapping
    public List<CertificatesDTO> getAllCertificates() {
        List<Certificates> certificatesList = certificatesRepository.findAll(); // Fetch all certificates from the repository
        return certificatesList.stream()
            .map(certificatesMapper::toDto) // Convert each entity to DTO
            .collect(Collectors.toList()); // Collect to a list
    }


    // DELETE Certificate by ID
    @DeleteMapping("/api/attendance-mgt/{id}")
    public void deleteCertificateById(@PathVariable Long id) {
        certificatesService.deleteCertificateById(id);
    }

    // UPDATE Certificate (alternative)
    /*@PutMapping("/{id}")
    public Certificates updateCertificate(@RequestBody CertificatesDTO certificatesDTO) {
        return certificatesService.updateCertificate(certificatesDTO);
    }*/


    @PutMapping("/api/attendance-mgt/{id}") // Assuming you're using PUT for updates
    public ResponseEntity<CertificatesDTO> updateCertificate(@PathVariable Long id, @RequestBody CertificatesDTO certificatesDTO) {
        certificatesDTO.setId(id); // Ensure the DTO has the ID set from the URL path
        CertificatesDTO updatedCertificate = certificatesService.updateCertificate(certificatesDTO);
        return ResponseEntity.ok(updatedCertificate); // Return the updated DTO with a 200 OK status
    }

}

