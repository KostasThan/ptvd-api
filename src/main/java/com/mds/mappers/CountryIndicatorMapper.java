package com.mds.mappers;

import com.mds.dao.entities.CountryIndicatorEntity;
import com.mds.domain.model.CountryIndicator;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface CountryIndicatorMapper {

    @Mapping(source = "country.name", target = "countryName")
    @Mapping(source = "indicator.code", target = "indicatorCode")
    CountryIndicator entityToModel(CountryIndicatorEntity countryEntity);

    List<CountryIndicator> entityToModel(List<CountryIndicatorEntity> countryEntity);
}
