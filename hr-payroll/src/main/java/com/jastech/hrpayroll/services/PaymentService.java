package com.jastech.hrpayroll.services;

import com.jastech.hrpayroll.entities.Payment;
import com.jastech.hrpayroll.entities.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    private final RestTemplate restTemplate;

    @Value("${hr-worker.host}")
    private String workerHost;

    @Autowired
    public PaymentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Payment getPayment(Long workerId, Integer days) {

        Map<String, String> urlVariables = new HashMap<>();
        urlVariables.put("id", workerId.toString());

        Worker worker = restTemplate.getForObject(workerHost + "/workers/{id}", Worker.class, urlVariables);

        assert worker != null;
        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }
}
