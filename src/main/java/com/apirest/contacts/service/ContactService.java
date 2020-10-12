package com.apirest.contacts.service;

import com.apirest.contacts.ContactNotFoundException;
import com.apirest.contacts.models.Contact;
import com.apirest.contacts.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository repository;

    public List<Contact> getAll() {
        return repository.findAll();
    }

    public Optional<Contact> getById(Long id) throws ContactNotFoundException {
        return repository.findById(id);
    }

    public Contact save(Contact contact) {
        return repository.save(contact);
    }

    public Contact update(Long id, Contact contact) {
        Optional<Contact> contacts = repository.findById(id);
        contacts.map(c -> {
            c.setName(contact.getName());
            c.setEmail(contact.getEmail());
            c.setPhone(contact.getPhone());

            Contact update = repository.save(c);

            return c;
        });

        return contact;
    }

    public void deleteById(Long id) {
        Optional<Contact> contact = repository.findById(id);
        repository.delete(contact.get());
    }
}
