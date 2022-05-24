package com.mds.dao.repositories;

import com.mds.dao.entities.MetricEntity;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Repository
public class MetricRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String QUERY_WITH_CODE_YEAR_FROM_YEAR_TO = "SELECT id,metric,year FROM %s " +
            "WHERE indicator_code = :indicatorCode " +
            "AND year >= :fromYear " +
            "AND year <= :toYear";

    private static final String QUERY_WITH_CODE_YEAR_FROM = "SELECT id,metric,year FROM %s " +
            "WHERE indicator_code = :indicatorCode " +
            "AND year >= :fromYear " +
            "LIMIT 1 ";

    private static final String METRICS_TABLE_PREFIX = "metrics_";

    public MetricRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<MetricEntity> findMetricsWithCriteria(String country, String indicatorCode, Integer fromYear, Integer toYear) {
        String metrics_query = setUpTableName(QUERY_WITH_CODE_YEAR_FROM_YEAR_TO, country);

        fromYear = fromYear != null ? fromYear : Integer.MIN_VALUE;
        toYear = toYear != null ? toYear : Integer.MAX_VALUE;

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("indicatorCode", indicatorCode)
                .addValue("fromYear", fromYear)
                .addValue("toYear", toYear);

        return namedParameterJdbcTemplate.query(metrics_query, namedParameters, new MetricRowNoIndicatorMapper());
    }

    public Optional<MetricEntity> findOldestMetric(String country, String indicatorCode, Integer fromYear) {
        String metrics_query = setUpTableName(QUERY_WITH_CODE_YEAR_FROM, country);

        fromYear = fromYear != null ? fromYear : Integer.MIN_VALUE;

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("indicatorCode", indicatorCode)
                .addValue("fromYear", fromYear);

        return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(metrics_query, namedParameters, new MetricRowNoIndicatorMapper()));
    }

    private String setUpTableName(String query, String country) {
        return String.format(query, configureTableName(country));
    }

    private String configureTableName(String country) {
        return METRICS_TABLE_PREFIX + country.toLowerCase(Locale.ROOT).replaceAll(" ","_");
    }
}
