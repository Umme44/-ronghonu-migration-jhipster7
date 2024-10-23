package com.bits.hr.domain;



import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;


/**
 * Certificates.
 */

@Entity
@Table(name = "certificates")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class Certificates implements Serializable {
    private static final long serialVersionUID = 1L;



    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "sequence_generator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "pin", nullable = false, unique = true)
    private String pin;


    @Column(name = "imageUrl")
    private String imageUrl;

    @Column(name = "description")
    private String description;


    @Column(name = "materialsLearned")
    private String materialsLearned;

    @Column(name = "enrollmentDate")
    private LocalDate enrollmentDate;

    @Column(name = "completionDate")
    private LocalDate completionDate;

    @Override
    public String toString() {
        return "Certificates{" +
            "id=" + id +
            ", pin='" + pin + '\'' +
            ", imageUrl='" + imageUrl + '\'' +
            ", description='" + description + '\'' +
            ", enrollmentDate=" + enrollmentDate +
            ", completionDate=" + completionDate +
            ", expirationDate=" + expirationDate +
            ", isExpired=" + isExpired +
            '}';
    }

    @Column(name = "expirationDate")
    private LocalDate expirationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull String getPin() {
        return pin;
    }

    public String getMaterialsLearned() {
        return materialsLearned;
    }

    public void setMaterialsLearned(String materialsLearned) {
        this.materialsLearned = materialsLearned;
    }

    public void setPin(@NotNull String pin) {
        this.pin = pin;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Column(name = "isExpired")
    private Boolean isExpired;


}



