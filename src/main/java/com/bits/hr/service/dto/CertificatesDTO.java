package com.bits.hr.service.dto;

import com.bits.hr.domain.enumeration.MaterialsLearned;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.time.LocalDate;

public class CertificatesDTO implements Serializable {
    @Setter
    @Getter
    private Long id;

    @Setter
    @Getter
    private String certificateImage;

    @Setter
    @Getter
    private String description;

    @Getter
    @Setter
    private MaterialsLearned materialsLearned;

    @Getter
    @Setter
    private LocalDate enrollmentDate;

    @Getter
    @Setter
    private LocalDate completionDate;

    @Setter
    @Getter
    private LocalDate expirationDate;

    @Getter
    @Setter
    private Boolean isExpired;


}
