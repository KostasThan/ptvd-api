package com.mds.dao.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "country_indicator")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class CountryIndicatorEntity {

    @Id
    private String id;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "country_name", referencedColumnName = CountryEntity_.NAME)
    private CountryEntity country;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "indicator_code", referencedColumnName = IndicatorEntity_.CODE)
    private IndicatorEntity indicator;

    @PrePersist
    private void setId(){
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CountryIndicatorEntity that = (CountryIndicatorEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
