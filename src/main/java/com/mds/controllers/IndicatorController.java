package com.mds.controllers;

import com.mds.domain.model.Indicator;
import com.mds.domain.model.IndicatorSearchCriteria;
import com.mds.domain.service.IndicatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IndicatorController implements IndicatorApi {

    private final IndicatorService indicatorService;

    public IndicatorController(IndicatorService indicatorService) {
        this.indicatorService = indicatorService;
    }

    @Override
    public ResponseEntity<Indicator> findById(String id) {
        return ResponseEntity.ok(indicatorService.findIndicatorById(id));
    }

    @Override
    public ResponseEntity<List<Indicator>> findByCriteria(List<String> id, List<String> code, String name, String sourceOrganization) {

        IndicatorSearchCriteria criteria = IndicatorSearchCriteria.builder().id(id)
                .code(code)
                .name(name)
                .sourceOrganization(sourceOrganization)
                .build();

        return ResponseEntity.ok(indicatorService.findByCriteria(criteria));
    }

}
