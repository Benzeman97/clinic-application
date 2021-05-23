package com.benz.norge.patient.visit.api.controller;

import com.benz.norge.patient.visit.api.entity.Patient;
import com.benz.norge.patient.visit.api.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/patient")
public class PatientController {

    private PatientService patientService;

    public PatientController(PatientService patientService){
        this.patientService=patientService;
    }

    @PostMapping(value = "/save",produces = {MediaType.APPLICATION_JSON_VALUE},consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Patient> savePatient(@RequestBody Patient patient){

        return (patient.getPatientId().trim().isEmpty() && patient.getPatientName().trim().isEmpty() && patient.getCreatedBy().trim().isEmpty()) ?
             new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
                new ResponseEntity<>(patientService.savePatient(patient),HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Patient> findPatient(@PathVariable("id") String patientId){
        return patientId.trim().isEmpty() ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(patientService.findPatient(patientId));
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Patient>> getPatients(){
        return ResponseEntity.ok(patientService.getPatients());
    }

    @PutMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE},consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Patient> updatePatient(@PathVariable("id") String patientId,@RequestBody Patient patient){
        return patientId.trim().isEmpty() ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
                new ResponseEntity<>(patientService.updatePatient(patientId,patient),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable("id") String patientId){
        if(patientId.trim().isEmpty())
            throw new IllegalArgumentException("patient is required");
        patientService.deletePatient(patientId);
    }
}
