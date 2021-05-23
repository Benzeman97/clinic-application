package com.benz.norge.patient.visit.api.service;

import com.benz.norge.patient.visit.api.entity.Physician;

import java.util.List;

public interface PhysicianService {

    Physician savePhysician(Physician physician);
    Physician findPhysician(String reg_no);
    List<Physician> getPhysicians();
    Physician updatePhysician(String reg_no,Physician physician);
    void deletePhysician(String reg_no);
}
