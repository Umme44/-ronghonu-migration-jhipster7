package com.bits.hr.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.bits.hr.domain.Certificatev2} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Certificatev2DTO implements Serializable {

    private Long id;

    private String pin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Certificatev2DTO)) {
            return false;
        }

        Certificatev2DTO certificatev2DTO = (Certificatev2DTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, certificatev2DTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Certificatev2DTO{" +
            "id=" + getId() +
            ", pin='" + getPin() + "'" +
            "}";
    }
}
