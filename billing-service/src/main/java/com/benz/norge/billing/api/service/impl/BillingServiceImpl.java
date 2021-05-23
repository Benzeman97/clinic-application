package com.benz.norge.billing.api.service.impl;

import com.benz.norge.billing.api.dao.BillingDao;
import com.benz.norge.billing.api.entity.Billing;
import com.benz.norge.billing.api.service.BillingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class BillingServiceImpl implements BillingService {

    final private static Logger LOGGER = LogManager.getLogger(BillingServiceImpl.class);

    private BillingDao billingDao;

    public BillingServiceImpl(BillingDao billingDao){
        this.billingDao=billingDao;
    }

    @Override
    public Billing makePayment(String visitId) {

        Billing billing=new Billing();
        billing.setBilledDateTime(LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()));
        billing.setVisitedId(visitId);
        billing=billingDao.save(billing);
        LOGGER.info(String.format("Bill has been created with %s"),billing.getId());
        return billing;
    }
}
