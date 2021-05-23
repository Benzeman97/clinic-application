package com.benz.norge.patient.visit.api.dao;

import com.benz.norge.patient.visit.api.entity.Physician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhysicianDao extends JpaRepository<Physician,String> {
}
