package com.mds.mappers;

import com.mds.dao.entities.MetricEntity;
import com.mds.domain.model.Metric;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface MetricMapper {

    Metric entityToModel(MetricEntity metricEntity);

    List<Metric> entityToModel(List<MetricEntity> metricEntity);
}
