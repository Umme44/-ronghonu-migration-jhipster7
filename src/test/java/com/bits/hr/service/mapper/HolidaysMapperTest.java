package com.bits.hr.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HolidaysMapperTest {

    private HolidaysMapper holidaysMapper;

    @BeforeEach
    public void setUp() {
        holidaysMapper = new HolidaysMapperImpl();
    }
}
