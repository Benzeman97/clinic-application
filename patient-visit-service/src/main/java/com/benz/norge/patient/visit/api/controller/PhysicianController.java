package com.benz.norge.patient.visit.api.controller;

import com.benz.norge.patient.visit.api.entity.Physician;
import com.benz.norge.patient.visit.api.service.PhysicianService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/physician")
public class PhysicianController {

    private PhysicianService physicianService;

    public PhysicianController(PhysicianService physicianService){
        this.physicianService=physicianService;
    }

    @PostMapping(value = "/save",produces = {MediaType.APPLICATION_JSON_VALUE},consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Physician> savePhysician(@RequestBody Physician physician){
        return (physician.getRegNo().trim().isEmpty() && physician.getName().trim().isEmpty() && physician.getCreatedBy().trim().isEmpty()) ?
                new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
                new ResponseEntity<>(physicianService.savePhysician(physician),HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Physician> findPhysician(@PathVariable("id") String reg_no){
        return reg_no.trim().isEmpty() ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(physicianService.findPhysician(reg_no));
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Physician>> getPhysicians(){
        return ResponseEntity.ok(physicianService.getPhysicians());
    }

    @PutMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE},consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Physician> updatePhysician(@PathVariable("id") String reg_no,@RequestBody Physician physician){
        return reg_no.trim().isEmpty() ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
                new ResponseEntity<>(physicianService.updatePhysician(reg_no,physician),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deletePhysician(@PathVariable("id") String reg_no){
        if(reg_no.trim().isEmpty())
            throw new IllegalArgumentException("register number is required");
        physicianService.deletePhysician(reg_no);
    }
}
