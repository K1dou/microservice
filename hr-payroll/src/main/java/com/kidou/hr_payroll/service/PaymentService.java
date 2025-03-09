package com.kidou.hr_payroll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kidou.hr_payroll.feignclients.WorkerFeignClient;
import com.kidou.hr_payroll.model.Payment;
import com.kidou.hr_payroll.model.Worker;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class PaymentService {

    @Autowired
    private WorkerFeignClient workerFeignClient;

    @CircuitBreaker(name = "hr-payroll", fallbackMethod = "getPaymentAlternative")
    public Payment getPayment(long workerId, int days) {

        Worker worker = workerFeignClient.findById(workerId).getBody();
        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }

    public Payment getPaymentAlternative(Long workerId, int days) {
        Payment payment = new Payment("Marcelo", 200.0, 5);

        return payment;
    }

}
