package g3.service.impl;

import g3.model.Contact;
import g3.model.ContactDTO;
import g3.model.PhoneNumber;
import g3.model.exceptions.InvalidContactException;
import g3.repository.ContactRepository;
import g3.repository.PhoneNumberRepository;
import g3.service.ContactService;
import g3.service.PhoneNumberService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    private final PhoneNumberService phoneNumberService;
    private final PhoneNumberRepository phoneNumberRepository;

    public ContactServiceImpl(ContactRepository contactRepository, PhoneNumberService phoneNumberService, PhoneNumberRepository phoneNumberRepository) {
        this.contactRepository = contactRepository;
        this.phoneNumberService = phoneNumberService;
        this.phoneNumberRepository = phoneNumberRepository;
    }

    @Override
    public List<Contact> findAll() {
        return this.contactRepository.findAll();
    }

    @Override
    public Contact findById(Long id) {
        return this.contactRepository.findById(id).orElseThrow(()-> new InvalidContactException("Invalid Contact!"));
    }

    @Override
    public Optional<Contact> findByName(String name) {
        return this.contactRepository.findByName(name);
    }



    @Override
    public Contact create(String name, String email, String phoneNumber,String type, String address) {

        Optional<PhoneNumber> existingPhoneNumber = phoneNumberRepository.findByPhoneNumber(phoneNumber);

        if (existingPhoneNumber.isPresent()) {
            // Phone number already exists, use the existing one
            PhoneNumber existingNumber = existingPhoneNumber.get();

            List<PhoneNumber> phoneNumbers = new ArrayList<>();
            phoneNumbers.add(existingNumber);

            Contact newContact = new Contact(name, email, phoneNumbers, address);
            return contactRepository.save(newContact);
        } else {
            // Phone number doesn't exist, create a new one
            PhoneNumber newPhoneNumber = new PhoneNumber(phoneNumber, type);
            PhoneNumber savedPhoneNumber = phoneNumberRepository.save(newPhoneNumber);

            List<PhoneNumber> phoneNumbers = new ArrayList<>();
            phoneNumbers.add(savedPhoneNumber);

            Contact newContact = new Contact(name, email, phoneNumbers, address);
            return contactRepository.save(newContact);
        }

    }

    @Override
    public Contact create(ContactDTO contactDTO) {
        List<PhoneNumber> phoneNumbers = new ArrayList<>();

        for (Long phoneNumberId : contactDTO.getPhoneNumberIds()) {
            PhoneNumber phoneNumber = phoneNumberService.findById(phoneNumberId);
            phoneNumbers.add(phoneNumber);
        }

        Contact newContact = new Contact(contactDTO.getName(), contactDTO.getEmail(), phoneNumbers, contactDTO.getAddress());
        return contactRepository.save(newContact);

    }


       /* List<PhoneNumber> phoneNumbers = new ArrayList<>();
        PhoneNumber newPhoneNumber = new PhoneNumber(phoneNumber, type);
        PhoneNumber savedPhoneNumber = this.phoneNumberRepository.save(newPhoneNumber);
        phoneNumbers.add(savedPhoneNumber);

        Contact newContact = new Contact(name,email,phoneNumbers,address);
        return this.contactRepository.save(newContact);*/


    @Override
    public Contact update(Long id, String name, String email, List<Long> phoneNumberIds) {

    Contact contact = contactRepository.findById(id).orElseThrow(()-> new InvalidContactException("Contact not found"));
    contact.setName(name);
    contact.setEmail(email);

    List<PhoneNumber> phoneNumbers = this.phoneNumberRepository.findAllById(phoneNumberIds);
    contact.setPhoneNumberList(phoneNumbers);
    return this.contactRepository.save(contact);

    }

    @Override
    public Contact update(Long id,ContactDTO contactDTO) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new InvalidContactException("Contact not found"));

        contact.setName(contactDTO.getName());
        contact.setEmail(contactDTO.getEmail());

        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        for (Long phoneNumberId : contactDTO.getPhoneNumberIds()) {
            PhoneNumber phoneNumber = phoneNumberService.findById(phoneNumberId);
            if (contactDTO.getPhoneNumberIds().contains(phoneNumberId)) {
                phoneNumberService.updateNumber(phoneNumberId, phoneNumber.getPhoneNumber());
                phoneNumberService.updateType(phoneNumberId, phoneNumber.getType());
            } else {
                PhoneNumber newPhoneNumber = phoneNumberService.create(phoneNumber.getPhoneNumber(), phoneNumber.getType());
                phoneNumbers.add(newPhoneNumber);

            }
            phoneNumbers.add(phoneNumber);
        }

        contact.setPhoneNumberList(phoneNumbers);

        return contactRepository.save(contact);
    }

    @Override
    public Contact deleteById(Long id) {
        Contact contact = contactRepository.findById(id).orElseThrow(()-> new InvalidContactException("Contact not found"));
        this.contactRepository.deleteById(id);
        return contact;

    }
}
