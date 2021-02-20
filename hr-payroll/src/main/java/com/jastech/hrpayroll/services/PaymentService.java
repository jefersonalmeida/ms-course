package com.jastech.hrpayroll.services;

import com.jastech.hrpayroll.entities.Payment;
import com.jastech.hrpayroll.entities.Worker;
import com.jastech.hrpayroll.interfaces.WorkerFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    private final WorkerFeignClient workerFeignClient;

    @Autowired
    public PaymentService(WorkerFeignClient workerFeignClient) {
        this.workerFeignClient = workerFeignClient;
    }


    public Payment getPayment(Long workerId, Integer days) {
        Worker worker = workerFeignClient.find(workerId).getBody();
        assert worker != null;
        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }
}
