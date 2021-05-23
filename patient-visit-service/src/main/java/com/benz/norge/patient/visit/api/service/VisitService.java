package com.benz.norge.patient.visit.api.service;

import com.benz.norge.patient.visit.api.entity.Visit;

import java.util.List;


public interface VisitService {

    Visit saveVisit(Visit visit);
    Visit findVisit(String visitedId);
    List<Visit> getVisits();
    Visit updateVisit(String visitedId,Visit visit);
    void deleteVisit(String visitedId);

}
