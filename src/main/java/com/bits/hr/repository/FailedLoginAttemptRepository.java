package com.bits.hr.repository;

import com.bits.hr.domain.FailedLoginAttempt;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the FailedLoginAttempt entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FailedLoginAttemptRepository extends JpaRepository<FailedLoginAttempt, Long> {
    @Query("select failedLoginAttempt from FailedLoginAttempt failedLoginAttempt where failedLoginAttempt.userName = :username")
    Optional<FailedLoginAttempt> findByUsername(String username);
}
