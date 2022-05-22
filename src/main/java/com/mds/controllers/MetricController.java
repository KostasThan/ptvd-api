package com.mds.controllers;

import com.mds.domain.model.Metric;
import com.mds.domain.model.MetricSearchCriteria;
import com.mds.domain.service.MetricService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MetricController implements MetricApi {

    private final MetricService metricService;

    public MetricController(MetricService metricService) {
        this.metricService = metricService;
    }

    @Override
    public ResponseEntity<List<Metric>> findByCriteria(String countryName, String indicatorCode, Integer aggregateByYears, Integer fromYear, Integer toYear) {
        if(aggregateByYears != null && aggregateByYears < 1) throw new UnsupportedOperationException("aggregateByYears should be a positive integer but was " + aggregateByYears);
        MetricSearchCriteria criteria = MetricSearchCriteria.builder()
                .countryName(countryName)
                .indicatorCode(indicatorCode)
                .aggregateByYears(aggregateByYears)
                .fromYear(fromYear)
                .toYear(toYear)
                .build();

        return ResponseEntity.ok(metricService.findByCriteria(criteria));
    }
}
