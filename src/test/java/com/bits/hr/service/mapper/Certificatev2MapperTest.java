package com.bits.hr.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Certificatev2MapperTest {

    private Certificatev2Mapper certificatev2Mapper;

    @BeforeEach
    public void setUp() {
        certificatev2Mapper = new Certificatev2MapperImpl();
    }
}
