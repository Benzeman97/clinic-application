package com.benz.norge.patient.visit.api.repository;

import com.benz.norge.patient.visit.api.entity.Holiday;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolidayMongoDao extends MongoRepository<Holiday,String> {
}
