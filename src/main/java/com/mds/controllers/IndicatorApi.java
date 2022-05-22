package com.mds.controllers;


import com.mds.domain.model.Indicator;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Api(value = "/indicators", tags = "Indicators")
public interface IndicatorApi {

    String BASE_URL = "/indicators";

    @Operation(summary = "Returns an Indicator by id")
    @GetMapping(value = BASE_URL + "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Indicator> findById(

            @Parameter(in = ParameterIn.PATH, description = "The id of the Indicator")
            @PathVariable(value = "id") String id
    );

    @Operation(summary = "Returns a List of Indicators by search criteria")
    @GetMapping(value = BASE_URL, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Indicator>> findByCriteria(
            @Parameter(in = ParameterIn.QUERY, description = "A list with the ids of the Indicators")
            @RequestParam(required = false) List<String> id,

            @Parameter(in = ParameterIn.QUERY, description = "A list with the codes of the Indicators")
            @RequestParam(required = false) List<String> code,

            @Parameter(in = ParameterIn.QUERY, description = "The name of the Indicator(s). Wildcard search is supported")
            @RequestParam(required = false) String name,

            @Parameter(in = ParameterIn.QUERY, description = "The source organizations of the Indicator(s). Wildcard search is supported")
            @RequestParam(required = false) String sourceOrganization
    );

}
