package com.mds.dao.repositories;

import com.mds.dao.entities.IndicatorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IndicatorRepository extends JpaRepository<IndicatorEntity, String>, JpaSpecificationExecutor<IndicatorEntity> {
}
