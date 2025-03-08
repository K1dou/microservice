package com.kidou.hr_payroll.service;

import org.springframework.stereotype.Service;

import com.kidou.hr_payroll.model.Payment;

@Service
public class PaymentService {

    public Payment getPayment(long workerId, int days) {
        return new Payment("Bob", 200.0, days);
    }

}
