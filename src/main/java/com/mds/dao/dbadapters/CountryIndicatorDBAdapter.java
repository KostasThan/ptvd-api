package com.mds.dao.dbadapters;

import com.mds.dao.entities.CountryEntity_;
import com.mds.dao.entities.CountryIndicatorEntity;
import com.mds.dao.entities.CountryIndicatorEntity_;
import com.mds.dao.entities.IndicatorEntity_;
import com.mds.dao.repositories.CountryIndicatorRepository;
import com.mds.domain.model.CountryIndicator;
import com.mds.domain.model.CountryIndicatorSearchCriteria;
import com.mds.mappers.CountryIndicatorMapper;
import com.mds.utils.SpecificationUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;

@Component
public class CountryIndicatorDBAdapter {

    private final CountryIndicatorRepository countryIndicatorRepository;

    private final CountryIndicatorMapper countryIndicatorMapper;

    public CountryIndicatorDBAdapter(CountryIndicatorRepository countryIndicatorRepository, CountryIndicatorMapper countryIndicatorMapper) {
        this.countryIndicatorRepository = countryIndicatorRepository;
        this.countryIndicatorMapper = countryIndicatorMapper;
    }


    public List<CountryIndicator> findByCriteria(CountryIndicatorSearchCriteria criteria) {
        Specification<CountryIndicatorEntity> specification = where(hasCountryName(criteria.getCountryName()))
                .and(hasIndicatorCode(criteria.getIndicatorCode()));

        List<CountryIndicatorEntity> countryIndicatorEntities = countryIndicatorRepository.findAll(specification);

        return countryIndicatorMapper.entityToModel(countryIndicatorEntities);
    }

    private Specification<CountryIndicatorEntity> hasCountryName(String countryName){
        return SpecificationUtils.equalsIgnoreCase(countryName,
                root -> root.get(CountryIndicatorEntity_.country).get(CountryEntity_.NAME)
        );
    }

    private Specification<CountryIndicatorEntity> hasIndicatorCode(String indicatorCode){
        return SpecificationUtils.equalsIgnoreCase(indicatorCode,
                root -> root.get(CountryIndicatorEntity_.indicator).get(IndicatorEntity_.CODE)
        );
    }
}
