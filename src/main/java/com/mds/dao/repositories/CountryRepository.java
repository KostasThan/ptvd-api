package com.mds.dao.repositories;

import com.mds.dao.entities.CountryEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;


public interface CountryRepository extends CrudRepository<CountryEntity, String>, JpaSpecificationExecutor<CountryEntity> {
}
