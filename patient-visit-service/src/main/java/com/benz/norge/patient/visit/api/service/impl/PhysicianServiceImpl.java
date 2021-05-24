package com.benz.norge.patient.visit.api.service.impl;

import com.benz.norge.patient.visit.api.dao.PhysicianDao;
import com.benz.norge.patient.visit.api.entity.Physician;
import com.benz.norge.patient.visit.api.exception.DataNotFoundException;
import com.benz.norge.patient.visit.api.exception.ExistedException;
import com.benz.norge.patient.visit.api.service.PhysicianService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class PhysicianServiceImpl implements PhysicianService {

    final private static Logger LOGGER = LogManager.getLogger(PhysicianServiceImpl.class);

    private PhysicianDao physicianDao;

    public PhysicianServiceImpl(PhysicianDao physicianDao){
        this.physicianDao=physicianDao;
    }

    @Override
    public Physician savePhysician(Physician physician) {

        Physician phy = physicianDao.findById(physician.getRegNo()).orElse(null);

        if(Objects.nonNull(phy)){
            LOGGER.error(String.format("physician is existed with %s",phy.getRegNo()));
            throw new ExistedException(String.format("physician is existed with %s",phy.getRegNo()));
        }
        physician.setCreatedDateTime(LocalDateTime.ofInstant(new Date().toInstant(),ZoneId.systemDefault()));
        physician.setModifiedDateTime(LocalDateTime.ofInstant(new Date().toInstant(),ZoneId.systemDefault()));
        LOGGER.info(String.format("physician has been saved with %s",physician.getRegNo()));
        return physicianDao.save(physician);
    }

    @Override
    public Physician findPhysician(String reg_no) {
        LOGGER.info(String.format("finding physician with %s",reg_no));
        return physicianDao.findById(reg_no).orElseThrow(()->new DataNotFoundException(String.format("physician is not found with %s",reg_no)));
    }

    @Override
    public List<Physician> getPhysicians() {

        List<Physician> physicians=physicianDao.findAll();

        if(physicians.size()<=0){
            LOGGER.error("no physician(s) available in DB");
            throw new DataNotFoundException("no physician(s) available in DB");
        }
        LOGGER.info("retrieve all the physicians");
        return physicians;
    }

    @Override
    public Physician updatePhysician(String reg_no, Physician physician) {

       Physician u_phy = physicianDao.findById(reg_no).orElseThrow(()->new DataNotFoundException(String.format("physician is not found with %s",reg_no)));

       u_phy.setName(physician.getName());
       u_phy.setModifiedBy(physician.getModifiedBy());
       u_phy.setModifiedDateTime(LocalDateTime.ofInstant(new Date().toInstant(),ZoneId.systemDefault()));
       LOGGER.info(String.format("physician has been updated with %s",reg_no));
       return physicianDao.save(u_phy);
    }

    @Override
    public void deletePhysician(String reg_no) {
        Physician phy = physicianDao.findById(reg_no).orElseThrow(()->new DataNotFoundException(String.format("physician is not found with %s",reg_no)));
        LOGGER.info(String.format("physician has been deleted with %s",reg_no));
        physicianDao.delete(phy);
    }
}
