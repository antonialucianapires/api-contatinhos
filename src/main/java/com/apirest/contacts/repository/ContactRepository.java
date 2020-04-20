package com.apirest.contacts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apirest.contacts.models.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long>{

	
}
