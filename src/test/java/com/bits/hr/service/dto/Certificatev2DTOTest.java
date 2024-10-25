package com.bits.hr.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.bits.hr.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class Certificatev2DTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(Certificatev2DTO.class);
        Certificatev2DTO certificatev2DTO1 = new Certificatev2DTO();
        certificatev2DTO1.setId(1L);
        Certificatev2DTO certificatev2DTO2 = new Certificatev2DTO();
        assertThat(certificatev2DTO1).isNotEqualTo(certificatev2DTO2);
        certificatev2DTO2.setId(certificatev2DTO1.getId());
        assertThat(certificatev2DTO1).isEqualTo(certificatev2DTO2);
        certificatev2DTO2.setId(2L);
        assertThat(certificatev2DTO1).isNotEqualTo(certificatev2DTO2);
        certificatev2DTO1.setId(null);
        assertThat(certificatev2DTO1).isNotEqualTo(certificatev2DTO2);
    }
}
