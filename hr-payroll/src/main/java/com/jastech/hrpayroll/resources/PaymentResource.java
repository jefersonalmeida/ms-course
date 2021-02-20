package com.jastech.hrpayroll.resources;

import com.jastech.hrpayroll.entities.Payment;
import com.jastech.hrpayroll.services.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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

    @HystrixCommand(fallbackMethod = "getPaymentAlternative")
    @GetMapping(value = "{workerId}/days/{days}")
    public ResponseEntity<Payment> getPayment(
            @PathVariable("workerId") Long workerId,
            @PathVariable("days") Integer days
    ) {
        Payment obj = service.getPayment(workerId, days);
        return ResponseEntity.ok().body(obj);
    }

    public ResponseEntity<Payment> getPaymentAlternative(Long workerId, Integer days) {
        Payment obj = new Payment("Server OFF", 0.0, days);
        return ResponseEntity.ok().body(obj);
    }
}
