package com.bits.hr.web.rest;

import com.bits.hr.errors.BadRequestAlertException;
import com.bits.hr.repository.Certificatev2Repository;
import com.bits.hr.service.Certificatev2Service;
import com.bits.hr.service.dto.Certificatev2DTO;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.bits.hr.domain.Certificatev2}.
 */
@RestController
@RequestMapping("/api/employee-mgt")
public class Certificatev2Resource {

    private final Logger log = LoggerFactory.getLogger(Certificatev2Resource.class);

    private static final String ENTITY_NAME = "certificatev2";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final Certificatev2Service certificatev2Service;

    private final Certificatev2Repository certificatev2Repository;

    public Certificatev2Resource(Certificatev2Service certificatev2Service, Certificatev2Repository certificatev2Repository) {
        this.certificatev2Service = certificatev2Service;
        this.certificatev2Repository = certificatev2Repository;
    }

    /**
     * {@code POST  /certificatev-2-s} : Create a new certificatev2.
     *
     * @param certificatev2DTO the certificatev2DTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new certificatev2DTO, or with status {@code 400 (Bad Request)} if the certificatev2 has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/certificatev-2-s")
    public ResponseEntity<Certificatev2DTO> createCertificatev2(@RequestBody Certificatev2DTO certificatev2DTO) throws URISyntaxException {
        log.debug("REST request to save Certificatev2 : {}", certificatev2DTO);
        if (certificatev2DTO.getId() != null) {
            throw new BadRequestAlertException("A new certificatev2 cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Certificatev2DTO result = certificatev2Service.save(certificatev2DTO);
        return ResponseEntity
            .created(new URI("/api/certificatev-2-s/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /certificatev-2-s/:id} : Updates an existing certificatev2.
     *
     * @param id the id of the certificatev2DTO to save.
     * @param certificatev2DTO the certificatev2DTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated certificatev2DTO,
     * or with status {@code 400 (Bad Request)} if the certificatev2DTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the certificatev2DTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/certificatev-2-s/{id}")
    public ResponseEntity<Certificatev2DTO> updateCertificatev2(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Certificatev2DTO certificatev2DTO
    ) throws URISyntaxException {
        log.debug("REST request to update Certificatev2 : {}, {}", id, certificatev2DTO);
        if (certificatev2DTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, certificatev2DTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!certificatev2Repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Certificatev2DTO result = certificatev2Service.update(certificatev2DTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, certificatev2DTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /certificatev-2-s/:id} : Partial updates given fields of an existing certificatev2, field will ignore if it is null
     *
     * @param id the id of the certificatev2DTO to save.
     * @param certificatev2DTO the certificatev2DTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated certificatev2DTO,
     * or with status {@code 400 (Bad Request)} if the certificatev2DTO is not valid,
     * or with status {@code 404 (Not Found)} if the certificatev2DTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the certificatev2DTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/certificatev-2-s/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Certificatev2DTO> partialUpdateCertificatev2(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Certificatev2DTO certificatev2DTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Certificatev2 partially : {}, {}", id, certificatev2DTO);
        if (certificatev2DTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, certificatev2DTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!certificatev2Repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Certificatev2DTO> result = certificatev2Service.partialUpdate(certificatev2DTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, certificatev2DTO.getId().toString())
        );
    }

    /**
     * {@code GET  /certificatev-2-s} : get all the certificatev2s.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of certificatev2s in body.
     */
    @GetMapping("/certificatev-2-s")
    public List<Certificatev2DTO> getAllCertificatev2s() {
        log.debug("REST request to get all Certificatev2s");
        return certificatev2Service.findAll();
    }

    /**
     * {@code GET  /certificatev-2-s/:id} : get the "id" certificatev2.
     *
     * @param id the id of the certificatev2DTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the certificatev2DTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/certificatev-2-s/{id}")
    public ResponseEntity<Certificatev2DTO> getCertificatev2(@PathVariable Long id) {
        log.debug("REST request to get Certificatev2 : {}", id);
        Optional<Certificatev2DTO> certificatev2DTO = certificatev2Service.findOne(id);
        return ResponseUtil.wrapOrNotFound(certificatev2DTO);
    }

    /**
     * {@code DELETE  /certificatev-2-s/:id} : delete the "id" certificatev2.
     *
     * @param id the id of the certificatev2DTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/certificatev-2-s/{id}")
    public ResponseEntity<Void> deleteCertificatev2(@PathVariable Long id) {
        log.debug("REST request to delete Certificatev2 : {}", id);
        certificatev2Service.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
