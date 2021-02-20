package com.jastech.hrpayroll.resources;

import com.jastech.hrpayroll.entities.Payment;
import com.jastech.hrpayroll.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "payments")
public class PaymentResource {

    private final PaymentService service;

    @Autowired
    public PaymentResource(PaymentService service) {
        this.service = service;
    }

    @GetMapping(value = "{workerId}/days/{days}")
    public ResponseEntity<Payment> getPayments(
            @PathVariable("workerId") Long workerId,
            @PathVariable("days") Integer days
    ) {
        Payment obj = service.getPayment(workerId, days);
        return ResponseEntity.ok().body(obj);
    }
}
