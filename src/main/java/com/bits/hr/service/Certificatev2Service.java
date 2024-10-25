package com.bits.hr.service;

import com.bits.hr.service.dto.Certificatev2DTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.bits.hr.domain.Certificatev2}.
 */
public interface Certificatev2Service {
    /**
     * Save a certificatev2.
     *
     * @param certificatev2DTO the entity to save.
     * @return the persisted entity.
     */
    Certificatev2DTO save(Certificatev2DTO certificatev2DTO);

    /**
     * Updates a certificatev2.
     *
     * @param certificatev2DTO the entity to update.
     * @return the persisted entity.
     */
    Certificatev2DTO update(Certificatev2DTO certificatev2DTO);

    /**
     * Partially updates a certificatev2.
     *
     * @param certificatev2DTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Certificatev2DTO> partialUpdate(Certificatev2DTO certificatev2DTO);

    /**
     * Get all the certificatev2s.
     *
     * @return the list of entities.
     */
    List<Certificatev2DTO> findAll();

    /**
     * Get the "id" certificatev2.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Certificatev2DTO> findOne(Long id);

    /**
     * Delete the "id" certificatev2.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
