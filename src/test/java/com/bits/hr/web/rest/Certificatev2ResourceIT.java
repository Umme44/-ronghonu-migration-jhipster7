package com.bits.hr.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.bits.hr.IntegrationTest;
import com.bits.hr.domain.Certificatev2;
import com.bits.hr.repository.Certificatev2Repository;
import com.bits.hr.service.dto.Certificatev2DTO;
import com.bits.hr.service.mapper.Certificatev2Mapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link Certificatev2Resource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class Certificatev2ResourceIT {

    private static final String DEFAULT_PIN = "AAAAAAAAAA";
    private static final String UPDATED_PIN = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/certificatev-2-s";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private Certificatev2Repository certificatev2Repository;

    @Autowired
    private Certificatev2Mapper certificatev2Mapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCertificatev2MockMvc;

    private Certificatev2 certificatev2;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Certificatev2 createEntity(EntityManager em) {
        Certificatev2 certificatev2 = new Certificatev2().pin(DEFAULT_PIN);
        return certificatev2;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Certificatev2 createUpdatedEntity(EntityManager em) {
        Certificatev2 certificatev2 = new Certificatev2().pin(UPDATED_PIN);
        return certificatev2;
    }

    @BeforeEach
    public void initTest() {
        certificatev2 = createEntity(em);
    }

    @Test
    @Transactional
    void createCertificatev2() throws Exception {
        int databaseSizeBeforeCreate = certificatev2Repository.findAll().size();
        // Create the Certificatev2
        Certificatev2DTO certificatev2DTO = certificatev2Mapper.toDto(certificatev2);
        restCertificatev2MockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(certificatev2DTO))
            )
            .andExpect(status().isCreated());

        // Validate the Certificatev2 in the database
        List<Certificatev2> certificatev2List = certificatev2Repository.findAll();
        assertThat(certificatev2List).hasSize(databaseSizeBeforeCreate + 1);
        Certificatev2 testCertificatev2 = certificatev2List.get(certificatev2List.size() - 1);
        assertThat(testCertificatev2.getPin()).isEqualTo(DEFAULT_PIN);
    }

    @Test
    @Transactional
    void createCertificatev2WithExistingId() throws Exception {
        // Create the Certificatev2 with an existing ID
        certificatev2.setId(1L);
        Certificatev2DTO certificatev2DTO = certificatev2Mapper.toDto(certificatev2);

        int databaseSizeBeforeCreate = certificatev2Repository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCertificatev2MockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(certificatev2DTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Certificatev2 in the database
        List<Certificatev2> certificatev2List = certificatev2Repository.findAll();
        assertThat(certificatev2List).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCertificatev2s() throws Exception {
        // Initialize the database
        certificatev2Repository.saveAndFlush(certificatev2);

        // Get all the certificatev2List
        restCertificatev2MockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(certificatev2.getId().intValue())))
            .andExpect(jsonPath("$.[*].pin").value(hasItem(DEFAULT_PIN)));
    }

    @Test
    @Transactional
    void getCertificatev2() throws Exception {
        // Initialize the database
        certificatev2Repository.saveAndFlush(certificatev2);

        // Get the certificatev2
        restCertificatev2MockMvc
            .perform(get(ENTITY_API_URL_ID, certificatev2.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(certificatev2.getId().intValue()))
            .andExpect(jsonPath("$.pin").value(DEFAULT_PIN));
    }

    @Test
    @Transactional
    void getNonExistingCertificatev2() throws Exception {
        // Get the certificatev2
        restCertificatev2MockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCertificatev2() throws Exception {
        // Initialize the database
        certificatev2Repository.saveAndFlush(certificatev2);

        int databaseSizeBeforeUpdate = certificatev2Repository.findAll().size();

        // Update the certificatev2
        Certificatev2 updatedCertificatev2 = certificatev2Repository.findById(certificatev2.getId()).get();
        // Disconnect from session so that the updates on updatedCertificatev2 are not directly saved in db
        em.detach(updatedCertificatev2);
        updatedCertificatev2.pin(UPDATED_PIN);
        Certificatev2DTO certificatev2DTO = certificatev2Mapper.toDto(updatedCertificatev2);

        restCertificatev2MockMvc
            .perform(
                put(ENTITY_API_URL_ID, certificatev2DTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(certificatev2DTO))
            )
            .andExpect(status().isOk());

        // Validate the Certificatev2 in the database
        List<Certificatev2> certificatev2List = certificatev2Repository.findAll();
        assertThat(certificatev2List).hasSize(databaseSizeBeforeUpdate);
        Certificatev2 testCertificatev2 = certificatev2List.get(certificatev2List.size() - 1);
        assertThat(testCertificatev2.getPin()).isEqualTo(UPDATED_PIN);
    }

    @Test
    @Transactional
    void putNonExistingCertificatev2() throws Exception {
        int databaseSizeBeforeUpdate = certificatev2Repository.findAll().size();
        certificatev2.setId(count.incrementAndGet());

        // Create the Certificatev2
        Certificatev2DTO certificatev2DTO = certificatev2Mapper.toDto(certificatev2);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCertificatev2MockMvc
            .perform(
                put(ENTITY_API_URL_ID, certificatev2DTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(certificatev2DTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Certificatev2 in the database
        List<Certificatev2> certificatev2List = certificatev2Repository.findAll();
        assertThat(certificatev2List).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCertificatev2() throws Exception {
        int databaseSizeBeforeUpdate = certificatev2Repository.findAll().size();
        certificatev2.setId(count.incrementAndGet());

        // Create the Certificatev2
        Certificatev2DTO certificatev2DTO = certificatev2Mapper.toDto(certificatev2);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCertificatev2MockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(certificatev2DTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Certificatev2 in the database
        List<Certificatev2> certificatev2List = certificatev2Repository.findAll();
        assertThat(certificatev2List).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCertificatev2() throws Exception {
        int databaseSizeBeforeUpdate = certificatev2Repository.findAll().size();
        certificatev2.setId(count.incrementAndGet());

        // Create the Certificatev2
        Certificatev2DTO certificatev2DTO = certificatev2Mapper.toDto(certificatev2);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCertificatev2MockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(certificatev2DTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Certificatev2 in the database
        List<Certificatev2> certificatev2List = certificatev2Repository.findAll();
        assertThat(certificatev2List).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCertificatev2WithPatch() throws Exception {
        // Initialize the database
        certificatev2Repository.saveAndFlush(certificatev2);

        int databaseSizeBeforeUpdate = certificatev2Repository.findAll().size();

        // Update the certificatev2 using partial update
        Certificatev2 partialUpdatedCertificatev2 = new Certificatev2();
        partialUpdatedCertificatev2.setId(certificatev2.getId());

        partialUpdatedCertificatev2.pin(UPDATED_PIN);

        restCertificatev2MockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCertificatev2.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCertificatev2))
            )
            .andExpect(status().isOk());

        // Validate the Certificatev2 in the database
        List<Certificatev2> certificatev2List = certificatev2Repository.findAll();
        assertThat(certificatev2List).hasSize(databaseSizeBeforeUpdate);
        Certificatev2 testCertificatev2 = certificatev2List.get(certificatev2List.size() - 1);
        assertThat(testCertificatev2.getPin()).isEqualTo(UPDATED_PIN);
    }

    @Test
    @Transactional
    void fullUpdateCertificatev2WithPatch() throws Exception {
        // Initialize the database
        certificatev2Repository.saveAndFlush(certificatev2);

        int databaseSizeBeforeUpdate = certificatev2Repository.findAll().size();

        // Update the certificatev2 using partial update
        Certificatev2 partialUpdatedCertificatev2 = new Certificatev2();
        partialUpdatedCertificatev2.setId(certificatev2.getId());

        partialUpdatedCertificatev2.pin(UPDATED_PIN);

        restCertificatev2MockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCertificatev2.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCertificatev2))
            )
            .andExpect(status().isOk());

        // Validate the Certificatev2 in the database
        List<Certificatev2> certificatev2List = certificatev2Repository.findAll();
        assertThat(certificatev2List).hasSize(databaseSizeBeforeUpdate);
        Certificatev2 testCertificatev2 = certificatev2List.get(certificatev2List.size() - 1);
        assertThat(testCertificatev2.getPin()).isEqualTo(UPDATED_PIN);
    }

    @Test
    @Transactional
    void patchNonExistingCertificatev2() throws Exception {
        int databaseSizeBeforeUpdate = certificatev2Repository.findAll().size();
        certificatev2.setId(count.incrementAndGet());

        // Create the Certificatev2
        Certificatev2DTO certificatev2DTO = certificatev2Mapper.toDto(certificatev2);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCertificatev2MockMvc
            .perform(
                patch(ENTITY_API_URL_ID, certificatev2DTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(certificatev2DTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Certificatev2 in the database
        List<Certificatev2> certificatev2List = certificatev2Repository.findAll();
        assertThat(certificatev2List).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCertificatev2() throws Exception {
        int databaseSizeBeforeUpdate = certificatev2Repository.findAll().size();
        certificatev2.setId(count.incrementAndGet());

        // Create the Certificatev2
        Certificatev2DTO certificatev2DTO = certificatev2Mapper.toDto(certificatev2);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCertificatev2MockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(certificatev2DTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Certificatev2 in the database
        List<Certificatev2> certificatev2List = certificatev2Repository.findAll();
        assertThat(certificatev2List).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCertificatev2() throws Exception {
        int databaseSizeBeforeUpdate = certificatev2Repository.findAll().size();
        certificatev2.setId(count.incrementAndGet());

        // Create the Certificatev2
        Certificatev2DTO certificatev2DTO = certificatev2Mapper.toDto(certificatev2);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCertificatev2MockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(certificatev2DTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Certificatev2 in the database
        List<Certificatev2> certificatev2List = certificatev2Repository.findAll();
        assertThat(certificatev2List).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCertificatev2() throws Exception {
        // Initialize the database
        certificatev2Repository.saveAndFlush(certificatev2);

        int databaseSizeBeforeDelete = certificatev2Repository.findAll().size();

        // Delete the certificatev2
        restCertificatev2MockMvc
            .perform(delete(ENTITY_API_URL_ID, certificatev2.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Certificatev2> certificatev2List = certificatev2Repository.findAll();
        assertThat(certificatev2List).hasSize(databaseSizeBeforeDelete - 1);
    }
}
