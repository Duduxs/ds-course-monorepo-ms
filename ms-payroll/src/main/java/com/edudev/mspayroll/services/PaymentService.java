package com.edudev.mspayroll.services;

import com.edudev.mspayroll.entities.Payment;
import com.edudev.mspayroll.entities.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${ms-worker.host}")
    private String workerHost;

    public Payment getPayment(UUID workerId, int days) {

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("id", workerId.toString());

        Worker worker = restTemplate.getForObject(workerHost + "/workers/{id}", Worker.class, uriVariables);

        return new Payment(worker.name(), worker.dailyIncome(), days);
    }

}
