package com.kyle.singlestonecodingchallenge;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Map;
import java.util.Objects;


@Entity
class Contact {

    private @Id @GeneratedValue Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;
    private String phoneType;
    private String email;


    Contact() {}

    Contact(String firstName, String middleName, String lastName, String street, String city, String state, String zip, String phone, String phoneType, String email) {

        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        this.phoneType = phoneType;
        this.email = email;

    }

    public Long getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }
    public String getMiddleName() {
        return this.middleName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public String getStreet() {
        return this.street;
    }
    public String getCity() {
        return this.city;
    }
    public String getState() {
        return this.state;
    }
    public String getZip() {
        return this.zip;
    }
    public String getPhone() {
        return this.phone;
    }
    public String getPhoneType() {
        return this.phoneType;
    }
    public String getEmail() {
        return this.email;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }
    public void setMiddleName(String name) {
        this.middleName = name;
    }
    public void setLastName(String name) {
        this.lastName = name;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setState(String state) {
        this.state = state;
    }
    public void setZip(String zip) {
        this.zip = zip;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setPhoneType(String phone) {
        this.phoneType = phone;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
