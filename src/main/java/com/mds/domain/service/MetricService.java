package com.mds.domain.service;

import com.mds.dao.dbadapters.MetricDBAdapter;
import com.mds.domain.model.Metric;
import com.mds.domain.model.MetricSearchCriteria;
import liquibase.repackaged.org.apache.commons.lang3.tuple.MutablePair;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MetricService {

    private final MetricDBAdapter metricDBAdapter;

    public MetricService(MetricDBAdapter metricDBAdapter) {
        this.metricDBAdapter = metricDBAdapter;
    }

    public List<Metric> findByCriteria(MetricSearchCriteria criteria) {
        List<Metric> metrics = metricDBAdapter.findByCriteria(criteria);

        if (shouldAggregateTheResults(criteria, metrics)) {
            Integer fromYear;
            if (criteria.getFromYear() == null) {
                fromYear = metricDBAdapter.findOldestMetricYearAfter(criteria.getCountryName(), criteria.getIndicatorCode(), criteria.getFromYear());
            } else {
                fromYear = criteria.getFromYear();
            }
            return aggregateMetrics(metrics, fromYear, criteria.getAggregateByYears());

        }
        return metrics;
    }

    private List<Metric> aggregateMetrics(List<Metric> metrics, Integer fromYear, Integer aggregateByYears) {

        HashMap<Integer, MutablePair<Integer, Double>> yearToOccurencesToValueMap = new HashMap<>();

        metrics.forEach( metric -> {
            Integer metricKey = calculateAggregatedYear(fromYear, aggregateByYears, metric.getYear());
            yearToOccurencesToValueMap.compute(metricKey, (k,v) -> v == null? new MutablePair<>(1, metric.getMetric()) : addValuesToPair(v,metric));
        });

        return yearToOccurencesToValueMap.entrySet().stream().map( e -> createAggregatedMetric(e, fromYear, aggregateByYears)).collect(Collectors.toList());
    }

    private MutablePair<Integer, Double> addValuesToPair(MutablePair<Integer, Double> pair, Metric metric) {
        pair.setLeft(pair.getLeft() + 1);
        pair.setValue(pair.getValue() + metric.getMetric());
        return pair;
    }

    private Integer calculateAggregatedYear(int fromYear, int aggregateByYears, int metricYear) {
        return (metricYear - fromYear) / aggregateByYears;
    }


    //the aggregation is only needed if provided by the user AND there was at least on metric found with the given criteria
    private boolean shouldAggregateTheResults(MetricSearchCriteria criteria, List<Metric> metrics) {
        return !metrics.isEmpty() && criteria.getAggregateByYears() != null;
    }

    private Metric createAggregatedMetric(Map.Entry<Integer, MutablePair<Integer, Double>> entrySet, int fromYear, int aggregateByYears){
        Metric metric = new Metric();
        metric.setYear(entrySet.getKey() * aggregateByYears + fromYear);

        MutablePair<Integer, Double> pair = entrySet.getValue();
        metric.setMetric(pair.getRight() / pair.getLeft());

        return metric;
    }
}
