package com.bits.hr.repository;

import com.bits.hr.domain.Certificatev2;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Certificatev2 entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Certificatev2Repository extends JpaRepository<Certificatev2, Long> {}
