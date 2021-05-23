package com.benz.norge.billing.api.service.impl;

import com.benz.norge.billing.api.dao.BillingDao;
import com.benz.norge.billing.api.entity.Billing;
import com.benz.norge.billing.api.model.Payment;
import com.benz.norge.billing.api.service.BillingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class BillingServiceImpl implements BillingService {

    final private static Logger LOGGER = LogManager.getLogger(BillingServiceImpl.class);

    private BillingDao billingDao;

    public BillingServiceImpl(BillingDao billingDao){
        this.billingDao=billingDao;
    }

    @Override
    public Payment makePayment(Payment payment) {

        Billing billing=new Billing();
        billing.setBilledDateTime(LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()));
        billing.setVisitedId(payment.getVisitedId());
        billing=billingDao.save(billing);
        LOGGER.info(String.format("Bill has been created with %s"),billing.getId());

        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd-MM-yy HH:mm");

        payment.setId(billing.getId());
        payment.setDate(billing.getBilledDateTime().format(formatter));
        payment.setVisitedId(billing.getVisitedId());

        return payment;
    }
}
