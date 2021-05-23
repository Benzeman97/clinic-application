package com.benz.norge.patient.visit.api.controller;

import com.benz.norge.patient.visit.api.entity.Visit;
import com.benz.norge.patient.visit.api.model.Payment;
import com.benz.norge.patient.visit.api.service.VisitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/visit")
public class VisitController {

    private VisitService visitService;

    public VisitController(VisitService visitService){
        this.visitService=visitService;
    }

    @PostMapping(value = "/save",produces = {MediaType.APPLICATION_JSON_VALUE},consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Visit> saveVisit(@RequestBody Visit visit){
        return (visit.getVisitedId().trim().isEmpty() && visit.getReason().trim().isEmpty() && visit.getVisitDateTime().trim().isEmpty()
        && visit.getPatient().getPatientId().trim().isEmpty() && visit.getPatient().getPatientName().trim().isEmpty() &&
                visit.getPhysician().getRegNo().trim().isEmpty() && visit.getPhysician().getName().trim().isEmpty()) ?
                new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
                new ResponseEntity<>(visitService.saveVisit(visit),HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Visit> findVisit(@PathVariable("id") String visitedId){
        return visitedId.trim().isEmpty() ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(visitService.findVisit(visitedId));
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Visit>> getVisits(){
        return ResponseEntity.ok(visitService.getVisits());
    }

    @PutMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE},consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Visit> updateVisit(@PathVariable("id") String visitedId,@RequestBody Visit visit){
        return (visitedId.trim().isEmpty() && visit.getPatient().getPatientId().trim().isEmpty() && visit.getPhysician().getRegNo().isEmpty()) ?
                new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
                new ResponseEntity<>(visitService.updateVisit(visitedId,visit),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteVisit(@PathVariable("id") String visitedId){
        if(visitedId.trim().isEmpty())
            throw new IllegalArgumentException("visitedId is required");
        visitService.deleteVisit(visitedId);
    }

    @PostMapping(value = "/payment",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Payment> makePayment(@RequestBody Payment payment) throws Exception {
        return payment.getVisitedId().trim().isEmpty() ?
                new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
                new ResponseEntity<>(visitService.makePayment(payment),HttpStatus.OK);
    }
}
