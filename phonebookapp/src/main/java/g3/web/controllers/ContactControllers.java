package g3.web.controllers;

import g3.model.Contact;
import g3.model.ContactDTO;
import g3.model.PhoneNumber;
import g3.model.exceptions.InvalidContactException;
import g3.model.exceptions.InvalidPhoneNumberException;
import g3.repository.PhoneNumberRepository;
import g3.service.ContactService;
import g3.service.PhoneNumberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contacts")
public class ContactControllers {

    private final ContactService contactService;
    private final PhoneNumberService phoneNumberService;
    private final PhoneNumberRepository phoneNumberRepository;

    public ContactControllers(ContactService contactService, PhoneNumberService phoneNumberService, PhoneNumberRepository phoneNumberRepository) {
        this.contactService = contactService;
        this.phoneNumberService = phoneNumberService;
        this.phoneNumberRepository = phoneNumberRepository;
    }

    // READ

    @GetMapping
    public List<Contact> findAll() {
        return this.contactService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> findById(@PathVariable Long id){
        try {
            Contact contact = contactService.findById(id);
            return ResponseEntity.ok(contact);
        } catch (InvalidContactException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/add")
    public ResponseEntity<Contact> save(@RequestBody Contact contactRequest) {
        try {
            Contact newContact = contactService.create(contactRequest.getName(), contactRequest.getEmail(),
                    contactRequest.getPhoneNumber(), contactRequest.getPhoneNumberType(),
                    contactRequest.getAddress());
            return ResponseEntity.status(HttpStatus.CREATED).body(newContact);
        } catch (InvalidContactException e){
            return ResponseEntity.notFound().build();
        }

    }

    public ResponseEntity<Contact> edit(@PathVariable Long id,@RequestBody ContactDTO) {
        try {
            Contact newContact = contactService.create(contactRequest.getName(), contactRequest.getEmail(),
                    contactRequest.getPhoneNumber(), contactRequest.getPhoneNumberType(),
                    contactRequest.getAddress());
            return ResponseEntity.status(HttpStatus.CREATED).body(newContact);
        } catch (InvalidContactException e){
            return ResponseEntity.notFound().build();
        }
    }



}
