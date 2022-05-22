package com.mds.app;


import com.mds.dao.repositories.CountryIndicatorRepository;
import com.mds.dao.repositories.CountryRepository;
import com.mds.dao.repositories.IndicatorRepository;
import com.mds.dao.repositories.MetricRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.mds", "com.mds.mappers"})
@EntityScan("com.mds.dao.entities")
@EnableJpaRepositories("com.mds.dao.repositories")
public class Application {

    private final CountryRepository countryRepository;

    private final IndicatorRepository indicatorRepository;

    private final CountryIndicatorRepository countryIndicatorRepository;

    private final MetricRepository metricRepository;

    public Application(CountryRepository countryRepository, IndicatorRepository indicatorRepository, CountryIndicatorRepository countryIndicatorRepository, MetricRepository metricRepository) {
        this.countryRepository = countryRepository;
        this.indicatorRepository = indicatorRepository;
        this.countryIndicatorRepository = countryIndicatorRepository;
        this.metricRepository = metricRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
