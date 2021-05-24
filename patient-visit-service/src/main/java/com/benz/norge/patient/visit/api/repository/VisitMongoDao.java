package com.benz.norge.patient.visit.api.repository;

import com.benz.norge.patient.visit.api.entity.Visit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitMongoDao extends MongoRepository<Visit,String> {
}
