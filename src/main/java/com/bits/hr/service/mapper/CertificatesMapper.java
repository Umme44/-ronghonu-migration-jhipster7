package com.bits.hr.service.mapper;

import com.bits.hr.domain.Certificates;
import com.bits.hr.service.dto.CertificatesDTO;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring")
public interface CertificatesMapper extends EntityMapper<CertificatesDTO, Certificates> {

}
