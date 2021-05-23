package com.benz.norge.billing.api.dao;

import com.benz.norge.billing.api.entity.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingDao extends JpaRepository<Billing,Integer> {
}
