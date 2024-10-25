package com.bits.hr.service.mapper;

import com.bits.hr.domain.Certificatev2;
import com.bits.hr.service.dto.Certificatev2DTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Certificatev2} and its DTO {@link Certificatev2DTO}.
 */
@Mapper(componentModel = "spring")
public interface Certificatev2Mapper extends EntityMapper<Certificatev2DTO, Certificatev2> {}
