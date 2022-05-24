package com.mds.dao.dbadapters;

import com.mds.dao.entities.CountryEntity;
import com.mds.dao.entities.CountryEntity_;
import com.mds.dao.repositories.CountryRepository;
import com.mds.domain.model.Country;
import com.mds.domain.model.CountrySearchCriteria;
import com.mds.mappers.CountryMapper;
import com.mds.utils.SpecificationUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.Specification.where;

@Component
public class CountryDBAdapter {

    private final CountryRepository countryRepository;

    private final CountryMapper countryMapper;

    public CountryDBAdapter(CountryRepository countryRepository, CountryMapper countryMapper) {
        this.countryRepository = countryRepository;
        this.countryMapper = countryMapper;
    }

    public Country hasCountryNameLike(String countryName){
        Specification<CountryEntity> specification = where(hasCountryNameLikeSpecification(countryName));

        List<CountryEntity> countryEntities = countryRepository.findAll(specification);

        if(countryEntities.isEmpty()){
            throw new RuntimeException(countryName);
        }

        return countryMapper.entityToModel(countryEntities.get(0));
    }

    public List<Country> findByCriteria(CountrySearchCriteria criteria) {
        Specification<CountryEntity> specification = where(hasCountryNameLikeSpecification(criteria.getName()));

        List<CountryEntity> countryEntities = countryRepository.findAll(specification);

        return countryMapper.entityToModel(countryEntities);
    }

    private Specification<CountryEntity> hasCountryNameLikeSpecification(String name) {
        return SpecificationUtils.likeIgnoreCase(name, CountryEntity_.NAME);
    }
}
