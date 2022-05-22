package com.mds.dao.repositories;

import com.mds.dao.entities.CountryIndicatorEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface CountryIndicatorRepository extends CrudRepository<CountryIndicatorEntity, String>, JpaSpecificationExecutor<CountryIndicatorEntity> {
}
