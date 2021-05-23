package com.benz.norge.patient.visit.api.dao;

import com.benz.norge.patient.visit.api.entity.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolidayDao extends JpaRepository<Holiday,String> {
}
