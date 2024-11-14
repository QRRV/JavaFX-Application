package com.example.codecademy;

import org.junit.Test;

import static org.junit.Assert.*;

public class Tests {
    @Test
    public void validateMailAddressReturnsFalseNoAt(){
        //arrange
        Validation validator = new Validation();
        String emailAddress = "dgmail.com";
        //act
        boolean result = Validation.validateEmail(emailAddress);

        //assert
        assertEquals(false, result);
    }

    @Test
    public void validateMailAddressReturnsTrue(){
        String emailAddress = "test@test.com";
        assertTrue(Validation.validateEmail(emailAddress));
    }

    @Test
    public void validateDateOfBirthReturnsFalse(){
        String date = "13-XX-2o23";
        assertFalse(Validation.validateDateOfBirth(date));
    }

    @Test
    public void validateDateOfBirthReturnsTrue(){
        String date = "2023-01-13";
        assertTrue(Validation.validateDateOfBirth(date));
    }

    @Test
    public void validateExpectedPercentageReturnsTrue(){
        Integer percent = 13;
        assertTrue(Validation.validatePercentage(percent));
    }

    @Test
    public void validateExpectedPercentageReturnsFalse(){
        Integer percent = 101;
        assertFalse(Validation.validatePercentage(percent));
    }

    @Test
    public void validateCertificaatCijferReturnsFalse(){
        Integer cijfer = 11;
        assertFalse(Validation.validateCertificaatCijfer(cijfer));
    }

    @Test
    public void validateCertificaatCijferReturnsTrue(){
        Integer cijfer = 6;
        assertTrue(Validation.validateCertificaatCijfer(cijfer));
    }

    @Test
    public void validatePostCodeReturnsTrue(){
        String postcd = "1234 AB";
        assertTrue(Validation.validatePostCode(postcd));
    }

    @Test
    public void validatePostCodeReturnsFalse(){
        String postcd = "123AB@";
        assertFalse(Validation.validatePostCode(postcd));
    }
}