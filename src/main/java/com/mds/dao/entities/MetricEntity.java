package com.mds.dao.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;


@Setter
@Getter
@RequiredArgsConstructor
public class MetricEntity implements Serializable {

    @Id
    private String id;

    @Column
    private int year;

    @Column
    private double metric;

    public int getYear() {
        return year;
    }

    public double getMetric() {
        return metric;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MetricEntity that = (MetricEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


    @PrePersist
    private void setId(){
        this.id = UUID.randomUUID().toString();
    }
}
