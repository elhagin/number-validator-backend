package com.jumia.numbervalidator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumia.numbervalidator.entity.PhoneNumber;
import com.jumia.numbervalidator.rest.ValidationRestController;
import com.jumia.numbervalidator.service.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    CustomerServiceImpl customerService;

    static PhoneNumber validNumber;
    static PhoneNumber invalidNumber;

    @BeforeAll
    public static void setUp() {
        validNumber = new PhoneNumber("Ethiopia", false, "(251)", "988200000");
        invalidNumber = new PhoneNumber("Uganda", true, "(212)", "6007989253");
    }

    @Test
    @DisplayName("A valid number should have the isValid property set to true after validation")
    public void testValidNumber() {
        PhoneNumber validatedNumber =
                customerService.validateNumber(validNumber.getCountryCode() + " " + validNumber.getNumber());
        assertTrue(validatedNumber.isValid());
    }

    @Test
    @DisplayName("An invalid number should have the isValid property set to false after validation")
    public void testInvalidNumber() {
        PhoneNumber validatedNumber =
                customerService.validateNumber(invalidNumber.getCountryCode() + " " + invalidNumber.getNumber());
        assertFalse(validatedNumber.isValid());
    }
}
