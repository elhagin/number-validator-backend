package com.jumia.numbervalidator.rest;

import com.jumia.numbervalidator.dao.CustomerDAO;
import com.jumia.numbervalidator.entity.Customer;
import com.jumia.numbervalidator.entity.PhoneNumber;
import com.jumia.numbervalidator.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ValidationRestController {

    private CustomerServiceImpl customerService;

    // inject CustomerService
    @Autowired
    public ValidationRestController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    // expose "/customers" to return list of customers
    @GetMapping("/numbers")
    public List<PhoneNumber> getAllCustomers() {
        return customerService.getCategorizedNumbers();
    }
}
