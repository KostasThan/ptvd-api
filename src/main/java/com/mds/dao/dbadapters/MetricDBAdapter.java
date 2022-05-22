package com.mds.dao.dbadapters;

import com.mds.dao.entities.*;
import com.mds.dao.repositories.MetricRepository;
import com.mds.domain.model.Metric;
import com.mds.domain.model.MetricSearchCriteria;
import com.mds.mappers.MetricMapper;
import com.mds.utils.SpecificationUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;

@Component
public class MetricDBAdapter {

    private final MetricRepository metricRepository;

    private final MetricMapper metricMapper;

    public MetricDBAdapter(MetricRepository metricRepository, MetricMapper metricMapper) {
        this.metricRepository = metricRepository;
        this.metricMapper = metricMapper;
    }

    public List<Metric> findByCriteria(MetricSearchCriteria criteria) {
        Specification<MetricEntity> specification = where(hasCountryName(criteria.getCountryName()))
                .and(hasIndicatorCode(criteria.getIndicatorCode()))
                .and(hasYearEqualsToOrAfter(criteria.getFromYear()))
                .and(hasYearLessThanOrEqualsTo(criteria.getToYear()));

        List<MetricEntity> metricEntities = metricRepository.findAll(specification);

        return metricMapper.entityToModel(metricEntities);
    }

    public int findOldestMetricYearAfter(String countryName, String indicatorCode, Integer fromYear) {
        Specification<MetricEntity> specification = where(hasCountryName(countryName))
                .and(hasIndicatorCode(indicatorCode))
                .and(hasYearEqualsToOrAfter(fromYear));


        Pageable limit = PageRequest.of(0, 1);
        Page<MetricEntity> oldestMetricAfter = metricRepository.findAll(specification, limit);

        MetricEntity metricEntity = oldestMetricAfter.get()
                .findAny()
                .orElseThrow(() ->
                        new RuntimeException(String.format("Cannot find oldest metric for country %s and indicator code %s after year %d",
                                countryName, indicatorCode, fromYear)));
        return metricEntity.getYear();
    }

    private Specification<MetricEntity> hasYearEqualsToOrAfter(Integer fromYear) {
        return SpecificationUtils.greaterThanOrEqualsTo(fromYear, MetricEntity_.YEAR);
    }

    private Specification<MetricEntity> hasYearLessThanOrEqualsTo(Integer toYear) {
        return SpecificationUtils.lessThanOrEqualTo(toYear, MetricEntity_.YEAR);
    }

    private Specification<MetricEntity> hasCountryName(String countryName) {

        return SpecificationUtils.equalsIgnoreCase(countryName,
                root -> root.get(MetricEntity_.countryIndicatorEntity).get(CountryIndicatorEntity_.country).get(CountryEntity_.NAME)
        );
    }

    private Specification<MetricEntity> hasIndicatorCode(String indicatorCode) {

        return SpecificationUtils.equalsIgnoreCase(indicatorCode,
                root -> root.get(MetricEntity_.countryIndicatorEntity).get(CountryIndicatorEntity_.indicator).get(IndicatorEntity_.CODE)
        );
    }
}
