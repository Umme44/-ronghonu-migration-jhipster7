package com.bits.hr.service.impl;

import com.bits.hr.domain.FailedLoginAttempt;
import com.bits.hr.repository.FailedLoginAttemptRepository;
import com.bits.hr.service.FailedLoginAttemptService;
import io.undertow.util.BadRequestException;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link FailedLoginAttempt}.
 */
@Service
@Transactional
public class FailedLoginAttemptServiceImpl implements FailedLoginAttemptService {

    private final Logger log = LoggerFactory.getLogger(FailedLoginAttemptServiceImpl.class);

    private final FailedLoginAttemptRepository failedLoginAttemptRepository;

    public FailedLoginAttemptServiceImpl(FailedLoginAttemptRepository failedLoginAttemptRepository) {
        this.failedLoginAttemptRepository = failedLoginAttemptRepository;
    }

    /*
     * if already failed attempt > 3 && lastTimeFailedAttempt < 25 min
     * -> BadRequestException("You have exceeded the maximum number of failed login attempts. Please try again after 25 minutes.")
     * */
    @Override
    public void validateFailedAttemptIfAny(String userName) {
        failedLoginAttemptRepository
            .findByUsername(userName)
            .ifPresent(failedLoginAttempt -> {
                if (
                    failedLoginAttempt.getContinuousFailedAttempts() > 3 &&
                    Duration.between(failedLoginAttempt.getLastFailedAttempt(), Instant.now()).toMinutes() < 25
                ) {
                    throw new BadCredentialsException(
                        "You have exceeded the maximum number of failed login attempts. Please try again after 25 minutes."
                    );
                }
            });
    }

    /*
     * if user id and password correct:
     * reset failed attempt
     * */
    @Override
    public void resetFailedAttemptIfPresent(String userName) {
        failedLoginAttemptRepository
            .findByUsername(userName)
            .ifPresent(failedLoginAttempt -> {
                failedLoginAttempt.setContinuousFailedAttempts(0);
                failedLoginAttempt.setLastFailedAttempt(Instant.now());
            });
    }

    /*
     * user id - pass wrong
     * -> if user id valid
     *   -> ++failed attempt
     * */
    @Override
    public void recordIncidentForBadCredentials(String userName) {
        Optional<FailedLoginAttempt> failedLoginAttempt = failedLoginAttemptRepository.findByUsername(userName);
        if (failedLoginAttempt.isPresent()) {
            failedLoginAttempt.get().setContinuousFailedAttempts(failedLoginAttempt.get().getContinuousFailedAttempts() + 1);
            failedLoginAttempt.get().setLastFailedAttempt(Instant.now());
            failedLoginAttemptRepository.save(failedLoginAttempt.get());
        } else {
            FailedLoginAttempt newFailedLoginAttempt = new FailedLoginAttempt();
            newFailedLoginAttempt.setUserName(userName);
            newFailedLoginAttempt.setContinuousFailedAttempts(1);
            newFailedLoginAttempt.setLastFailedAttempt(Instant.now());
            failedLoginAttemptRepository.save(newFailedLoginAttempt);
        }
    }
}
