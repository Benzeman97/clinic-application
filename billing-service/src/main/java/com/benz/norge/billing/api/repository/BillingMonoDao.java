package com.benz.norge.billing.api.repository;

import com.benz.norge.billing.api.entity.Billing;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingMonoDao extends MongoRepository<Billing,Long> {
}
