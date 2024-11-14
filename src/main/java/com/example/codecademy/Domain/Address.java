

package com.example.codecademy.Domain;

public class Address {
    private String emailAddress;
    private String streetName;
    private String city;
    private String zipCode;
    private int houseNumber;
    private String houseNumberAdd;
    private String country;

    public String getHouseNumberAdd() {
        return houseNumberAdd;
    }

    public void setHouseNumberAdd(String houseNumberAdd) {
        this.houseNumberAdd = houseNumberAdd;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "Address{" +
                "emailAddress='" + emailAddress + '\'' +
                ", streetName='" + streetName + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", houseNumber=" + houseNumber +
                ", houseNumberAdd='" + houseNumberAdd + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
