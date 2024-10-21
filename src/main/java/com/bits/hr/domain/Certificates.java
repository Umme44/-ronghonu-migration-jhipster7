package com.bits.hr.domain;



import com.bits.hr.domain.enumeration.MaterialsLearned;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
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



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Certificates that = (Certificates) o;
        return Objects.equals(id, that.id) && Objects.equals(certificateImage, that.certificateImage) && Objects.equals(description, that.description) && Objects.equals(materialsLearned, that.materialsLearned) && Objects.equals(enrollmentDate, that.enrollmentDate) && Objects.equals(completionDate, that.completionDate) && Objects.equals(expirationDate, that.expirationDate) && Objects.equals(isExpired, that.isExpired);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, certificateImage, description, materialsLearned, enrollmentDate, completionDate, expirationDate, isExpired);
    }



}
