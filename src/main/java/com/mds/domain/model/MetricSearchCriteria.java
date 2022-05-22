package com.mds.domain.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MetricSearchCriteria {

    private String countryName;

    private String indicatorCode;

    private Integer aggregateByYears;

    private Integer fromYear;

    private Integer toYear;
}
