package com.mds.dao.entities;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "indicators")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class IndicatorEntity implements Serializable {

    @Id
    private String id;

    @NotNull
    private String code;

    @NotNull
    @Column(length = 10000)
    private String name;

    @NotNull
    @Column(length = 1000)
    private String description;

    @NotNull
    @Column(length = 1000)
    private String sourceOrganization;

    @PrePersist
    private void setId(){
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        IndicatorEntity that = (IndicatorEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
