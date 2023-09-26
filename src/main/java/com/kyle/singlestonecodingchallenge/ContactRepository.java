package com.kyle.singlestonecodingchallenge;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findAllByPhoneType(String phoneType);
}
