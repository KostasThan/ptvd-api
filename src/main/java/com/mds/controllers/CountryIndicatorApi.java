package com.mds.controllers;


import com.mds.domain.model.CountryIndicator;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Api(value = "/country-indicators", tags = "Country-Indicators")
public interface CountryIndicatorApi {

    String BASE_URL = "/country-indicators";

    @Operation(summary = "Returns a list of all Country Indicators by the given Criteria")
    @GetMapping(value = BASE_URL, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<CountryIndicator>> findByCriteria(
            @Parameter(in = ParameterIn.QUERY, description = "The name of the country")
            @RequestParam(required = false) String countryName,

            @Parameter(in = ParameterIn.QUERY, description = "The code of the Indicator")
            @RequestParam(required = false) String indicatorCode

    );
}
