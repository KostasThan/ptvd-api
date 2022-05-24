package com.mds.dao.repositories;

import com.mds.dao.entities.MetricEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class MetricRowNoIndicatorMapper implements RowMapper<MetricEntity> {

    @Override
    public MetricEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        MetricEntity metric = new MetricEntity();

        metric.setId(rs.getString(1));
        metric.setMetric(rs.getDouble(2));
        metric.setYear(rs.getInt(3));

        return metric;
    }
}
