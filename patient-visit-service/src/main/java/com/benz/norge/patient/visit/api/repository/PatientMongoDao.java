package com.benz.norge.patient.visit.api.repository;

import com.benz.norge.patient.visit.api.entity.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientMongoDao extends MongoRepository<Patient,String> {
}
