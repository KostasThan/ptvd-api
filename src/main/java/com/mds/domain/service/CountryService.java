package com.mds.domain.service;

import com.mds.dao.dbadapters.CountryDBAdapter;
import com.mds.domain.model.Country;
import com.mds.domain.model.CountrySearchCriteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private final CountryDBAdapter countryDBAdapter;

    public CountryService(CountryDBAdapter countryDBAdapter) {
        this.countryDBAdapter = countryDBAdapter;
    }

    public Country findCountryByName(String id){
        return countryDBAdapter.findCountryByName(id);
    }

    public List<Country> findByCriteria(CountrySearchCriteria criteria) {
        return countryDBAdapter.findByCriteria(criteria);
    }
}
