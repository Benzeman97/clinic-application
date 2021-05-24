package com.benz.norge.patient.visit.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = {"com.benz.norge.patient.visit.api.repository"})
@EnableJpaRepositories(basePackages = {"com.benz.norge.patient.visit.api.dao"})
@SpringBootApplication
public class PatientVisitServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientVisitServiceApplication.class, args);
    }

}
