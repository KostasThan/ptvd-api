package com.mds.controllers;

import com.mds.domain.model.Country;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Api(value = "/countries", tags = "Countries")
public interface CountryApi {

    String BASE_URL = "/countries";

    @Operation(summary = "Returns a list of all Countries in database")
    @GetMapping(value = BASE_URL, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Country>> findByCriteria(
            @Parameter(in = ParameterIn.QUERY, description = "The name of the Country. Wildcard search is supported")
            @RequestParam(required = false, value = "name") String name
    );


}
