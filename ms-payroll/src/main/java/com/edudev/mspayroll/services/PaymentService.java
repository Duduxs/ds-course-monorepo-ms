package com.edudev.mspayroll.services;

import com.edudev.mspayroll.entities.Payment;
import com.edudev.mspayroll.entities.Worker;
import com.edudev.mspayroll.feignclients.WorkerFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private WorkerFeignClient workerFeignClient;

    public Payment getPayment(Long workerId, int days) {

        Worker worker = workerFeignClient.findById(workerId).getBody();

        return new Payment(worker.name(), worker.dailyIncome(), days);

    }

}
