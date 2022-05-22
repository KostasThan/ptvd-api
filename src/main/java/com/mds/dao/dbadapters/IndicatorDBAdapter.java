package com.mds.dao.dbadapters;

import com.mds.dao.entities.IndicatorEntity;
import com.mds.dao.entities.IndicatorEntity_;
import com.mds.dao.repositories.IndicatorRepository;
import com.mds.domain.model.Indicator;
import com.mds.domain.model.IndicatorSearchCriteria;
import com.mds.mappers.IndicatorMapper;
import com.mds.utils.SpecificationUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.Specification.where;

@Component
public class IndicatorDBAdapter {

    private final IndicatorRepository indicatorRepository;

    private final IndicatorMapper indicatorMapper;

    public IndicatorDBAdapter(IndicatorRepository indicatorRepository, IndicatorMapper indicatorMapper) {
        this.indicatorRepository = indicatorRepository;
        this.indicatorMapper = indicatorMapper;
    }

    public List<Indicator> findAllIndicators() {
        List<IndicatorEntity> indicatorEntities = indicatorRepository.findAll();

        return indicatorMapper.entityToModel(indicatorEntities);
    }

    public Indicator findIndicatorById(String id) {
        Optional<IndicatorEntity> indicatorOptional = indicatorRepository.findById(id);

        if (indicatorOptional.isPresent()) {
            return indicatorMapper.entityToModel(indicatorOptional.get());
        }
        throw new RuntimeException(id);
    }

    public List<Indicator> findByCriteria(IndicatorSearchCriteria criteria) {
        Specification<IndicatorEntity> specification = where(hasIdIn(criteria.getId()))
                .and(hasCodeIn(criteria.getCode()))
                .and(hasShortDescriptionLike(criteria.getName()))
                .and(hasSourceOrganizationLike(criteria.getSourceOrganization()));

        List<IndicatorEntity> indicatorEntities = indicatorRepository.findAll(specification);

        return indicatorMapper.entityToModel(indicatorEntities);
    }


    private Specification<IndicatorEntity> hasIdIn(Collection<String> ids) {
        return SpecificationUtils.inListObject(ids, IndicatorEntity_.ID);
    }

    private Specification<IndicatorEntity> hasCodeIn(Collection<String> codes) {
        return SpecificationUtils.inListIgnoreCase(codes, IndicatorEntity_.CODE);
    }

    private Specification<IndicatorEntity> hasSourceOrganizationLike(String sourceOrganization) {
        return SpecificationUtils.likeIgnoreCase(sourceOrganization, IndicatorEntity_.SOURCE_ORGANIZATION);
    }

    private Specification<IndicatorEntity> hasShortDescriptionLike(String shortDescription) {
        return SpecificationUtils.likeIgnoreCase(shortDescription, IndicatorEntity_.NAME);
    }
}
