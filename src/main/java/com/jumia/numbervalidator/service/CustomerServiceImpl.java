package com.jumia.numbervalidator.service;

import com.jumia.numbervalidator.dao.CustomerDAO;
import com.jumia.numbervalidator.entity.Customer;
import com.jumia.numbervalidator.entity.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final String CAMEROON_COUNTRY = "Cameroon";
    private static final String ETHIOPIA_COUNTRY = "Ethiopia";
    private static final String MOROCCO_COUNTRY = "Morocco";
    private static final String MOZAMBIQUE_COUNTRY = "Mozambique";
    private static final String UGANDA_COUNTRY = "Uganda";
    private CustomerDAO customerDAO;

    @Autowired
    public CustomerServiceImpl(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    public List<PhoneNumber> getCategorizedNumbers() {
        List<Customer> customers = customerDAO.getAllCustomers();
        List<PhoneNumber> numbers = new ArrayList<>();
        for (Customer customer : customers) {
            PhoneNumber number = validateNumber(customer.getPhone());
            numbers.add(number);
        }
        return numbers;
    }

    @Override
    public PhoneNumber validateNumber(String number) {
        if (number.isBlank()) {
            return new PhoneNumber();
        }
        Pattern cameroonPattern = Pattern.compile("(\\(237\\))\\ ?([2368]\\d{7,8}$)");
        Pattern ethiopiaPattern = Pattern.compile("(\\(251\\))\\ ?([1-59]\\d{8}$)");
        Pattern moroccoPattern = Pattern.compile("(\\(212\\))\\ ?([5-9]\\d{8}$)");
        Pattern mozambiquePattern = Pattern.compile("(\\(258\\))\\ ?([28]\\d{7,8}$)");
        Pattern ugandaPattern = Pattern.compile("(\\(256\\))\\ ?(\\d{9}$)");
        PhoneNumber phoneNumber = new PhoneNumber();

        checkPattern(number, cameroonPattern, phoneNumber, CAMEROON_COUNTRY);
        if (!phoneNumber.isValid()) {
            checkPattern(number, ethiopiaPattern, phoneNumber, ETHIOPIA_COUNTRY);
        }
        if (!phoneNumber.isValid()) {
            checkPattern(number, moroccoPattern, phoneNumber, MOROCCO_COUNTRY);
        }
        if (!phoneNumber.isValid()) {
            checkPattern(number, mozambiquePattern, phoneNumber, MOZAMBIQUE_COUNTRY);
        }
        if (!phoneNumber.isValid()) {
            checkPattern(number, ugandaPattern, phoneNumber, UGANDA_COUNTRY);
        }
        return phoneNumber;
    }

    private void checkPattern(String number, Pattern pattern, PhoneNumber phoneNumber, String country) {
        Matcher matcher = pattern.matcher(number);
        if (matcher.matches()) {
            setPhoneNumberFields(phoneNumber, matcher.group(1), matcher.group(2), country, true);
        } else {
            String[] numberSplit = number.split("\\)");
            String countryCode = numberSplit[0] + ')';
            String invalidNumber = numberSplit[1].strip();
            setPhoneNumberFields(phoneNumber, countryCode, invalidNumber, country, false);
        }
    }

    private void setPhoneNumberFields(PhoneNumber phoneNumber, String countryCode, String number, String country, boolean isValid) {
        phoneNumber.setCountryCode(countryCode);
        phoneNumber.setNumber(number);
        phoneNumber.setCountry(country);
        phoneNumber.setValid(isValid);
    }
}
