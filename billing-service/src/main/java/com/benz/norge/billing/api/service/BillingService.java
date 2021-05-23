package com.benz.norge.billing.api.service;

import com.benz.norge.billing.api.entity.Billing;
import com.benz.norge.billing.api.model.Payment;

public interface BillingService {

    Payment makePayment(Payment payment);

}
