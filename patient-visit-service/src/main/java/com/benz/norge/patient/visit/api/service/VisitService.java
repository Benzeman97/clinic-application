package com.benz.norge.patient.visit.api.service;

import com.benz.norge.patient.visit.api.entity.Visit;
import com.benz.norge.patient.visit.api.model.Payment;

import java.util.List;


public interface VisitService {

    Visit saveVisit(Visit visit);
    Visit findVisit(String visitedId);
    List<Visit> getVisits();
    Visit updateVisit(String visitedId,Visit visit);
    void deleteVisit(String visitedId);
    Payment makePayment(Payment payment) throws Exception;

}
