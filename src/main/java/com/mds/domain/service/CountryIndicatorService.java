package com.mds.domain.service;

import com.mds.dao.dbadapters.CountryIndicatorDBAdapter;
import com.mds.domain.model.CountryIndicator;
import com.mds.domain.model.CountryIndicatorSearchCriteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryIndicatorService {

    private final CountryIndicatorDBAdapter countryIndicatorDBAdapter;

    public CountryIndicatorService(CountryIndicatorDBAdapter countryIndicatorDBAdapter) {
        this.countryIndicatorDBAdapter = countryIndicatorDBAdapter;
    }

    public List<CountryIndicator> findByCriteria(CountryIndicatorSearchCriteria criteria){
        return countryIndicatorDBAdapter.findByCriteria(criteria);
    }
}
