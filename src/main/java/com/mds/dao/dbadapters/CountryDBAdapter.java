package com.mds.dao.dbadapters;

import com.mds.dao.entities.CountryEntity;
import com.mds.dao.entities.CountryEntity_;
import com.mds.dao.entities.CountryIndicatorEntity;
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

    public List<Country> findAllCountries(){
        List<CountryEntity> countryEntities= countryRepository.findAll(null);

        return countryMapper.entityToModel(countryEntities);
    }
    public Country findCountryByName(String id){
        Optional<CountryEntity> countryOptional = countryRepository.findById(id);

        if(countryOptional.isPresent()){
            return countryMapper.entityToModel(countryOptional.get());
        }

        throw new RuntimeException(id);
    }

    public List<Country> findByCriteria(CountrySearchCriteria criteria) {
        Specification<CountryEntity> specification = where(hasCountryNameLike(criteria.getName()));

        List<CountryEntity> countryEntities = countryRepository.findAll(specification);

        return countryMapper.entityToModel(countryEntities);
    }

    private Specification<CountryEntity> hasCountryNameLike(String name) {
        return SpecificationUtils.likeIgnoreCase(name, CountryEntity_.NAME);
    }
}
