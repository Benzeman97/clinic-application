package com.benz.norge.billing.api.service;

import com.benz.norge.billing.api.entity.Billing;

public interface BillingService {

    Billing makePayment(String visitId);

}
