package com.benz.norge.patient.visit.api.service.impl;

import com.benz.norge.patient.visit.api.dao.VisitDao;
import com.benz.norge.patient.visit.api.entity.Visit;
import com.benz.norge.patient.visit.api.exception.DataNotFoundException;
import com.benz.norge.patient.visit.api.exception.ExistedException;
import com.benz.norge.patient.visit.api.service.VisitService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class VisitServiceImpl implements VisitService {

    final private static Logger LOGGER= LogManager.getLogger(VisitServiceImpl.class);

    private VisitDao visitDao;

    public VisitServiceImpl(VisitDao visitDao){
        this.visitDao=visitDao;
    }

    @Override
    public Visit saveVisit(Visit visit) {
        Visit s_visit = visitDao.findById(visit.getVisitedId()).orElse(null);

        if(Objects.nonNull(s_visit)){
            LOGGER.error(String.format("visit is existed with %s",s_visit.getVisitedId()));
            throw new ExistedException(String.format("visit is existed with %s",s_visit.getVisitedId()));
        }

            visit.setCreatedDateTime(LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()));
            visit.setModifiedDateTime(LocalDateTime.ofInstant(new Date().toInstant(),ZoneId.systemDefault()));
            LOGGER.info(String.format("visit has been saved with %s",visit.getVisitedId()));

        return visitDao.save(visit);
    }

    @Override
    public Visit findVisit(String visitedId) {
        return visitDao.findById(visitedId).orElseThrow(()-> new DataNotFoundException(String.format("visit is not found with %s",visitedId)));
    }

    @Override
    public List<Visit> getVisits() {
        List<Visit> visits=visitDao.findAll();
        if(visits.size()<=0){
            LOGGER.error("No visit(s) available");
            throw new DataNotFoundException("No visit(s) available");
        }
        LOGGER.info("retrieve all the visits");
        return visits;
    }


    @Override
    public Visit updateVisit(String visitedId, Visit visit) {

        Visit u_visit =visitDao.findById(visitedId).orElseThrow(()-> new DataNotFoundException(String.format("visit is not found with %s",visitedId)));

        u_visit.setReason(visit.getReason());
        u_visit.setModifiedBy(visit.getModifiedBy());
        u_visit.setModifiedDateTime(LocalDateTime.ofInstant(new Date().toInstant(),ZoneId.systemDefault()));

        LOGGER.info(String.format("visit has been updated with %s",visit.getVisitedId()));
        return visitDao.save(u_visit);
    }

    @Override
    public void deleteVisit(String visitedId) {
        Visit visit =visitDao.findById(visitedId).orElseThrow(()-> new DataNotFoundException(String.format("visit is not found with %s",visitedId)));
        LOGGER.info(String.format("visit has been deleted with %s",visitedId));
        visitDao.delete(visit);
    }
}
