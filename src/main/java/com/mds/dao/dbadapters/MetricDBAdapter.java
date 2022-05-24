package com.mds.dao.dbadapters;

import com.mds.dao.entities.MetricEntity;
import com.mds.dao.repositories.MetricRepository;
import com.mds.domain.model.Metric;
import com.mds.domain.model.MetricSearchCriteria;
import com.mds.mappers.MetricMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MetricDBAdapter {

    private final MetricMapper metricMapper;

    private final MetricRepository metricCustomRepository;

    public MetricDBAdapter(MetricMapper metricMapper, MetricRepository metricCustomRepository) {
        this.metricMapper = metricMapper;
        this.metricCustomRepository = metricCustomRepository;
    }

    public List<Metric> findByCriteria(MetricSearchCriteria criteria) {
        List<MetricEntity> metricEntities = metricCustomRepository.findMetricsWithCriteria(criteria.getCountryName(), criteria.getIndicatorCode(),
                criteria.getFromYear(), criteria.getToYear());

        return metricMapper.entityToModel(metricEntities);
    }

    public int findOldestMetricYearAfter(String countryName, String indicatorCode, Integer fromYear) {
        MetricEntity metricEntity = metricCustomRepository.findOldestMetric(countryName, indicatorCode, fromYear).orElseThrow(() ->
                new RuntimeException(String.format("Cannot find oldest metric for country %s and indicator code %s after year %d",
                        countryName, indicatorCode, fromYear)));

        return metricEntity.getYear();
    }

}
