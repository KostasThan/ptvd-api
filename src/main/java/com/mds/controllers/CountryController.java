package com.mds.controllers;

import com.mds.domain.model.Country;
import com.mds.domain.model.CountrySearchCriteria;
import com.mds.domain.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryController implements CountryApi{

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public ResponseEntity<List<Country>> findByCriteria(String countryName) {
        CountrySearchCriteria criteria = CountrySearchCriteria.builder().name(countryName).build();
        return ResponseEntity.ok(countryService.findByCriteria(criteria));
    }

}
