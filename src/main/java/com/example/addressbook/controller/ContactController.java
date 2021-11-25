package com.example.addressbook.controller;

import com.example.addressbook.exceptions.ResourceNotFoundException;
import com.example.addressbook.models.Contact;
import com.example.addressbook.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {
    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Contact> getContactById(@PathVariable(value = "id") Long contactId)
            throws ResourceNotFoundException {
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found for this id :: " + contactId));
        return ResponseEntity.ok().body(contact);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Contact createContact(@Valid @RequestBody Contact contact) {
        return contactRepository.save(contact);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Contact> updateContact(@PathVariable(value = "id") Long contactId,
                                                  @Valid @RequestBody Contact contactDetails) throws ResourceNotFoundException {
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found for this id :: " + contactId));

        contact.setFirstName(contactDetails.getFirstName());
        contact.setMiddleName(contactDetails.getMiddleName());
        contact.setLastName(contactDetails.getLastName());
        contact.setPhoneNumber(contactDetails.getPhoneNumber());
        contact.setEmail(contactDetails.getEmail());
        contact.setNickname(contactDetails.getNickname());
        final Contact updatedContact = contactRepository.save(contact);
        return ResponseEntity.ok(updatedContact);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Boolean> deleteContact(@PathVariable(value = "id") Long contactId)
            throws ResourceNotFoundException {
        Contact employee = contactRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + contactId));

        contactRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
