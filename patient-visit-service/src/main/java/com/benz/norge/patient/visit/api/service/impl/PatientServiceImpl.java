package com.benz.norge.patient.visit.api.service.impl;

import com.benz.norge.patient.visit.api.dao.PatientDao;
import com.benz.norge.patient.visit.api.entity.Patient;
import com.benz.norge.patient.visit.api.exception.DataNotFoundException;
import com.benz.norge.patient.visit.api.exception.PatientExistedException;
import com.benz.norge.patient.visit.api.service.PatientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class PatientServiceImpl implements PatientService {

    private PatientDao patientDao;
    final private static Logger LOGGER = LogManager.getLogger(PatientServiceImpl.class);

    public PatientServiceImpl(PatientDao patientDao){
        this.patientDao=patientDao;
    }

    @Override
    public Patient savePatient(Patient patient) {

         Patient n_pat = patientDao.findById(patient.getPatientId()).orElse(null);

         if(Objects.nonNull(n_pat)){
             LOGGER.error(String.format("Patient is existed with %s",n_pat.getPatientId()));
             throw new PatientExistedException(String.format("Patient is existed with %s",n_pat.getPatientId()));
         }

         patient.setCreatedDateTime(LocalDateTime.ofInstant(new Date().toInstant(),ZoneId.systemDefault()));
         patient.setModifiedDateTime(LocalDateTime.ofInstant(new Date().toInstant(),ZoneId.systemDefault()));
        LOGGER.info(String.format("patient has been saved with %s",patient.getPatientId()));
        return patientDao.save(patient);
    }

    @Override
    public Patient findPatient(String patientId) {
        LOGGER.info(String.format("find patient with %s",patientId));
        return patientDao.findById(patientId).orElseThrow(()->new DataNotFoundException(String.format("Patient is not found with %s",patientId)));
    }

    @Override
    public List<Patient> getPatients() {

        List<Patient> patients = patientDao.findAll();
        if(patients.size()<=0) {
            LOGGER.error("No Patient(s) Available in DB");
            throw new DataNotFoundException("No Patient(s) Available in DB");
        }
        return patients;
    }

    @Override
    public Patient updatePatient(String patientId, Patient patient) {

        Patient u_patient = patientDao.findById(patientId).orElseThrow(()->new DataNotFoundException(String.format("Patient is not found with %s",patientId)));

        u_patient.setPatientName(patient.getPatientName());
        u_patient.setGender(patient.getGender());
        u_patient.setAge(patient.getAge());
        u_patient.setCreatedBy(patient.getCreatedBy());
        u_patient.setModifiedDateTime(LocalDateTime.ofInstant(new Date().toInstant(),ZoneId.systemDefault()));

        LOGGER.info(String.format("Patient has been updated with %s",u_patient.getPatientId()));
        return u_patient;
    }

    @Override
    public void deletePatient(String patientId) {

        Patient patient = patientDao.findById(patientId).orElseThrow(()->new DataNotFoundException(String.format("Patient is not found with %s",patientId)));
        LOGGER.info(String.format("patient has been deleted %s",patient.getPatientId()));
        patientDao.delete(patient);
    }
}
