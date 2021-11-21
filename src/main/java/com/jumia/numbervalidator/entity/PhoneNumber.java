package com.jumia.numbervalidator.entity;

public class PhoneNumber {
    private String country;
    private boolean isValid;
    private String countryCode;
    private String number;

    public PhoneNumber() {
    }

    public PhoneNumber(String country, boolean isValid, String countryCode, String number) {
        this.country = country;
        this.isValid = isValid;
        this.countryCode = countryCode;
        this.number = number;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "country='" + country + '\'' +
                ", isValid=" + isValid +
                ", countryCode='" + countryCode + '\'' +
                ", number=" + number +
                '}';
    }
}
