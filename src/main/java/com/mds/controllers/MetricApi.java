package com.mds.controllers;

import com.mds.domain.model.Metric;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Api(value = "/metric", tags = "Metrics")
@Validated
public interface MetricApi {

    String BASE_URL = "/metrics";

    @Operation(summary = "Returns a List of Indicators by search criteria")
    @GetMapping(value = BASE_URL, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Metric>> findByCriteria(
            @Parameter(in = ParameterIn.QUERY, description = "The country name")
            @RequestParam String countryName,

            @Parameter(in = ParameterIn.QUERY, description = "The indicator code")
            @RequestParam String indicatorCode,

            @Parameter(in = ParameterIn.QUERY, description = "For how many year to average the results")
            @RequestParam(required = false) Integer aggregateByYears,

            @Parameter(in = ParameterIn.QUERY, description = "From what year(inclusive) and onwards to get the results")
            @RequestParam(required = false) Integer fromYear,

            @Parameter(in = ParameterIn.QUERY, description = "The last year(inclusive) a metric should be")
            @RequestParam(required = false) Integer toYear
    );


}
