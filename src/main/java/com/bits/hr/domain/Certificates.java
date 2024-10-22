package com.bits.hr.domain;



import com.bits.hr.domain.enumeration.MaterialsLearned;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;


/**
 * Certificates.
 */

@Entity
@Table(name = "certificates")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class Certificates implements Serializable {
    private static final long serialVersionUID = 1L;



    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "sequence_generator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "pin", nullable = false, unique = true)
    private String pin;


    @Setter
    @Getter
    @Column(name = "certificateImage")
    private String certificateImage;

    @Setter
    @Getter
    @Column(name = "description")
    private String description;

    @Setter
    @Getter
    @Enumerated(EnumType.STRING)
    @Column(name = "materialsLearned")
    private MaterialsLearned materialsLearned;

    @Setter
    @Getter
    @Column(name = "enrollmentDate")
    private LocalDate enrollmentDate;

    @Setter
    @Getter
    @Column(name = "completionDate")
    private LocalDate completionDate;


    @Setter
    @Getter
    @Column(name = "expirationDate")
    private LocalDate expirationDate;

    @Setter
    @Getter
    @Column(name = "isExpired")
    private Boolean isExpired;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull String getPin() {
        return pin;
    }

    public void setPin(@NotNull String pin) {
        this.pin = pin;
    }

    public String getCertificateImage() {
        return certificateImage;
    }

    public void setCertificateImage(String certificateImage) {
        this.certificateImage = certificateImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MaterialsLearned getMaterialsLearned() {
        return materialsLearned;
    }

    public void setMaterialsLearned(MaterialsLearned materialsLearned) {
        this.materialsLearned = materialsLearned;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Boolean getExpired() {
        return isExpired;
    }

    public void setExpired(Boolean expired) {
        isExpired = expired;
    }
}



