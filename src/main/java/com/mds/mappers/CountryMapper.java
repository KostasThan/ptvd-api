package com.mds.mappers;

import com.mds.dao.entities.CountryEntity;
import com.mds.domain.model.Country;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CountryMapper {

    Country entityToModel(CountryEntity countryEntity);

    List<Country> entityToModel(List<CountryEntity> countryEntity);
}
