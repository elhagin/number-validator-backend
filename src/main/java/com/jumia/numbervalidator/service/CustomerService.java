package com.jumia.numbervalidator.service;

import com.jumia.numbervalidator.entity.Customer;
import com.jumia.numbervalidator.entity.PhoneNumber;

import java.util.List;

public interface CustomerService {

    public List<PhoneNumber> getCategorizedNumbers();
    public PhoneNumber validateNumber(String number);
}
