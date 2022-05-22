package com.mds.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CountrySearchCriteria {

    private String name;
}
