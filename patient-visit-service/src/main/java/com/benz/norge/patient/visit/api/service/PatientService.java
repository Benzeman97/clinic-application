package com.benz.norge.patient.visit.api.service;

import com.benz.norge.patient.visit.api.entity.Patient;

import java.util.List;

public interface PatientService {

    Patient savePatient(Patient patient);
    Patient findPatient(String patientId);
    List<Patient> getPatients();
    Patient updatePatient(String patientId,Patient patient);
    void deletePatient(String patientId);
}
