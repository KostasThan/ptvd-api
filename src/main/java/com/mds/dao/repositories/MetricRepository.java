package com.mds.dao.repositories;

import com.mds.dao.entities.MetricEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MetricRepository extends JpaRepository<MetricEntity, String>, JpaSpecificationExecutor<MetricEntity> {
}
