package com.mds.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CountryIndicatorSearchCriteria {

    private String countryName;

    private String indicatorCode;
}
