package com.mds.controllers;

import com.mds.domain.model.CountryIndicator;
import com.mds.domain.model.CountryIndicatorSearchCriteria;
import com.mds.domain.service.CountryIndicatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryIndicatorController implements CountryIndicatorApi {

    private final CountryIndicatorService countryIndicatorService;

    public CountryIndicatorController(CountryIndicatorService countryIndicatorService) {
        this.countryIndicatorService = countryIndicatorService;
    }

    @Override
    public ResponseEntity<List<CountryIndicator>> findByCriteria(String countryName, String indicatorCode) {

        CountryIndicatorSearchCriteria criteria = CountryIndicatorSearchCriteria.builder()
                .countryName(countryName)
                .indicatorCode(indicatorCode)
                .build();

        return ResponseEntity.ok(countryIndicatorService.findByCriteria(criteria));
    }
}
