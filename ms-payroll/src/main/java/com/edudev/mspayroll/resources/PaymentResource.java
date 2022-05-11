package com.edudev.mspayroll.resources;

import com.edudev.mspayroll.entities.Payment;
import com.edudev.mspayroll.services.PaymentService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/payments")
public class PaymentResource {

    @Autowired
    private PaymentService service;

    @GetMapping("/{workerId}/days/{days}")
    @CircuitBreaker(name = "circuitbreakerInstance", fallbackMethod = "getPaymentFallback")
    public ResponseEntity<Payment> getPayment(@PathVariable Long workerId, @PathVariable int days) {
        Payment payment = service.getPayment(workerId, days);

        return ResponseEntity.ok(payment);
    }

    public ResponseEntity<Payment> getPaymentFallback(Long workerId, int days, Throwable t) {
        Payment payment = new Payment("Brann", BigDecimal.valueOf(400.0), days);

        return ResponseEntity.ok(payment);
    }

}
