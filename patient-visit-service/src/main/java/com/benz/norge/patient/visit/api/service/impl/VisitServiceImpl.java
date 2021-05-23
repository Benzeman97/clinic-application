package com.benz.norge.patient.visit.api.service.impl;

import com.benz.norge.patient.visit.api.dao.HolidayDao;
import com.benz.norge.patient.visit.api.dao.VisitDao;
import com.benz.norge.patient.visit.api.entity.Holiday;
import com.benz.norge.patient.visit.api.entity.Visit;
import com.benz.norge.patient.visit.api.exception.DataNotFoundException;
import com.benz.norge.patient.visit.api.exception.ExistedException;
import com.benz.norge.patient.visit.api.exception.HolidayException;
import com.benz.norge.patient.visit.api.model.Payment;
import com.benz.norge.patient.visit.api.service.VisitService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class VisitServiceImpl implements VisitService {

    final private static Logger LOGGER= LogManager.getLogger(VisitServiceImpl.class);

    private VisitDao visitDao;
    private HolidayDao holidayDao;
    private RestTemplate restTemplate;

    @Value("${billing.service.url}")
    private String url;

    public VisitServiceImpl(VisitDao visitDao,HolidayDao holidayDao,RestTemplate restTemplate){
        this.visitDao=visitDao;
        this.holidayDao=holidayDao;
        this.restTemplate=restTemplate;
    }

    @Override
    public Visit saveVisit(Visit visit) {
        Visit s_visit = visitDao.findById(visit.getVisitedId()).orElse(null);

        if(Objects.nonNull(s_visit)){
            LOGGER.error(String.format("visit is existed with %s",s_visit.getVisitedId()));
            throw new ExistedException(String.format("visit is existed with %s",s_visit.getVisitedId()));
        }

        Holiday isHoliday = holidayDao.findById(visit.getVisitDateTime()).orElse(null);
        if(Objects.nonNull(isHoliday))
        {
            LOGGER.error(String.format("%s is a holiday",isHoliday.getHolidayDate()));
            throw new HolidayException(String.format("%s is a holiday",isHoliday.getHolidayDate()));
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

        Holiday isHoliday = holidayDao.findById(visit.getVisitDateTime()).orElse(null);
        if(Objects.nonNull(isHoliday))
        {
            LOGGER.error(String.format("%s is a holiday",isHoliday.getHolidayDate()));
            throw new HolidayException(String.format("%s is a holiday",isHoliday.getHolidayDate()));
        }

        u_visit.setReason(visit.getReason());
        u_visit.setVisitDateTime(visit.getVisitDateTime());
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

    @Override
    public Payment makePayment(Payment payment) throws Exception {
        Visit visit=visitDao.findById(payment.getVisitedId()).orElseThrow(()->new DataNotFoundException(String.format("visit is not found with %s",payment.getVisitedId())));
        URI uri=new URI(url);

        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.set("Content-Type","application/json");
        httpHeaders.set("Accept","application/json");

        HttpEntity<Payment> request=new HttpEntity<>(payment,httpHeaders);

        Payment response = restTemplate.postForEntity(uri,request,Payment.class).getBody();

        payment.setId(response.getId());
        payment.setDate(response.getDate());
        payment.setVisitedId(response.getVisitedId());
        payment.setPatientName(visit.getPatient().getPatientName());
        payment.setPhysicianName(visit.getPhysician().getName());

        LOGGER.info(String.format("payment transaction is done with %d",response.getId()));
        return payment;
    }
}
