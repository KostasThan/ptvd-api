package com.mds.dao.entities;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Table(name = "countries")
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class CountryEntity implements Serializable {

    @Id
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryEntity that = (CountryEntity) o;
        return Objects.equals(name, that.name);
    }

    @Override

    public int hashCode() {
        return Objects.hash(name);
    }
}
