package com.bits.hr.repository;

import com.bits.hr.domain.Certificates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificatesRepository extends JpaRepository<Certificates, Long> {
    Certificates findByPin(String pin);
    //Certificate findByPin(String pin);

}
