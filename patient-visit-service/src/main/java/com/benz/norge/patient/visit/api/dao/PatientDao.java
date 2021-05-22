package com.benz.norge.patient.visit.api.dao;

import com.benz.norge.patient.visit.api.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientDao extends JpaRepository<Patient,String> {
}
