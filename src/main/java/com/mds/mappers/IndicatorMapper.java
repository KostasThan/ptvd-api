package com.mds.mappers;

import com.mds.dao.entities.IndicatorEntity;
import com.mds.domain.model.Indicator;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface IndicatorMapper {

    Indicator entityToModel(IndicatorEntity entity);

    List<Indicator> entityToModel(List<IndicatorEntity> entity);
}
