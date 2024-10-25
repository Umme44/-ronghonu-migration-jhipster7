package com.bits.hr.service.impl;

import com.bits.hr.domain.Certificatev2;
import com.bits.hr.repository.Certificatev2Repository;
import com.bits.hr.service.Certificatev2Service;
import com.bits.hr.service.dto.Certificatev2DTO;
import com.bits.hr.service.mapper.Certificatev2Mapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Certificatev2}.
 */
@Service
@Transactional
public class Certificatev2ServiceImpl implements Certificatev2Service {

    private final Logger log = LoggerFactory.getLogger(Certificatev2ServiceImpl.class);

    private final Certificatev2Repository certificatev2Repository;

    private final Certificatev2Mapper certificatev2Mapper;

    public Certificatev2ServiceImpl(Certificatev2Repository certificatev2Repository, Certificatev2Mapper certificatev2Mapper) {
        this.certificatev2Repository = certificatev2Repository;
        this.certificatev2Mapper = certificatev2Mapper;
    }

    @Override
    public Certificatev2DTO save(Certificatev2DTO certificatev2DTO) {
        log.debug("Request to save Certificatev2 : {}", certificatev2DTO);
        Certificatev2 certificatev2 = certificatev2Mapper.toEntity(certificatev2DTO);
        certificatev2 = certificatev2Repository.save(certificatev2);
        return certificatev2Mapper.toDto(certificatev2);
    }

    @Override
    public Certificatev2DTO update(Certificatev2DTO certificatev2DTO) {
        log.debug("Request to update Certificatev2 : {}", certificatev2DTO);
        Certificatev2 certificatev2 = certificatev2Mapper.toEntity(certificatev2DTO);
        certificatev2 = certificatev2Repository.save(certificatev2);
        return certificatev2Mapper.toDto(certificatev2);
    }

    @Override
    public Optional<Certificatev2DTO> partialUpdate(Certificatev2DTO certificatev2DTO) {
        log.debug("Request to partially update Certificatev2 : {}", certificatev2DTO);

        return certificatev2Repository
            .findById(certificatev2DTO.getId())
            .map(existingCertificatev2 -> {
                certificatev2Mapper.partialUpdate(existingCertificatev2, certificatev2DTO);

                return existingCertificatev2;
            })
            .map(certificatev2Repository::save)
            .map(certificatev2Mapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Certificatev2DTO> findAll() {
        log.debug("Request to get all Certificatev2s");
        return certificatev2Repository.findAll().stream().map(certificatev2Mapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Certificatev2DTO> findOne(Long id) {
        log.debug("Request to get Certificatev2 : {}", id);
        return certificatev2Repository.findById(id).map(certificatev2Mapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Certificatev2 : {}", id);
        certificatev2Repository.deleteById(id);
    }
}
