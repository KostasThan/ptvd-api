package com.mds.domain.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class IndicatorSearchCriteria {

    private List<String> id;

    private List<String> code;

    private String name;

    private String sourceOrganization;
}
