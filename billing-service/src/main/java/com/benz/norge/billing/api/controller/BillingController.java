package com.benz.norge.billing.api.controller;

import com.benz.norge.billing.api.entity.Billing;
import com.benz.norge.billing.api.service.BillingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/billing")
public class BillingController {

    private BillingService billingService;

    public BillingController(BillingService billingService){
        this.billingService=billingService;
    }

    @PostMapping(value = "/pay/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Billing> makePayment(@PathVariable("id") String visitedId){
        return visitedId.trim().isEmpty() ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
                new ResponseEntity<>(billingService.makePayment(visitedId),HttpStatus.OK);
    }
}
