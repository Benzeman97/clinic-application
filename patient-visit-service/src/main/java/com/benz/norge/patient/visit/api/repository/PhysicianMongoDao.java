package com.benz.norge.patient.visit.api.repository;

import com.benz.norge.patient.visit.api.entity.Physician;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhysicianMongoDao extends MongoRepository<Physician,String> {
}
