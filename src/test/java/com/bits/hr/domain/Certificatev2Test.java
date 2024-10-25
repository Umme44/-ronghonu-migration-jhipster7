package com.bits.hr.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.bits.hr.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class Certificatev2Test {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Certificatev2.class);
        Certificatev2 certificatev21 = new Certificatev2();
        certificatev21.setId(1L);
        Certificatev2 certificatev22 = new Certificatev2();
        certificatev22.setId(certificatev21.getId());
        assertThat(certificatev21).isEqualTo(certificatev22);
        certificatev22.setId(2L);
        assertThat(certificatev21).isNotEqualTo(certificatev22);
        certificatev21.setId(null);
        assertThat(certificatev21).isNotEqualTo(certificatev22);
    }
}
