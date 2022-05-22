package com.mds.domain.service;

import com.mds.dao.dbadapters.IndicatorDBAdapter;
import com.mds.domain.model.Indicator;
import com.mds.domain.model.IndicatorSearchCriteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndicatorService {

    private final IndicatorDBAdapter indicatorDBAdapter;

    public IndicatorService(IndicatorDBAdapter indicatorDBAdapter) {
        this.indicatorDBAdapter = indicatorDBAdapter;
    }


    public List<Indicator> findAllIndicators() {
        return indicatorDBAdapter.findAllIndicators();
    }

    public Indicator findIndicatorById(String id) {
        return indicatorDBAdapter.findIndicatorById(id);

    }

    public List<Indicator> findByCriteria(IndicatorSearchCriteria criteria) {
        return indicatorDBAdapter.findByCriteria(criteria);
    }
}
