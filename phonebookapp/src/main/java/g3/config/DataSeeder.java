package mk.ukim.finki.wp.kol2022.g3.config;

import g3.model.Contact;
import g3.model.PhoneNumber;
import mk.ukim.finki.wp.kol2022.g3.repository.ContactRepository;
import mk.ukim.finki.wp.kol2022.g3.repository.PhoneNumberRepository;
import g3.service.ContactService;
import g3.service.PhoneNumberService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataSeeder {
    private final ContactRepository contactRepository;
    private final PhoneNumberRepository phoneNumberRepository;

    private final ContactService contactService;

    private final PhoneNumberService phoneNumberService;

    public DataSeeder(ContactRepository contactRepository, PhoneNumberRepository phoneNumberRepository, ContactService contactService, PhoneNumberService phoneNumberService) {
        this.contactRepository = contactRepository;
        this.phoneNumberRepository = phoneNumberRepository;
        this.contactService = contactService;
        this.phoneNumberService = phoneNumberService;
    }

    @PostConstruct
    public void seedData() {
        // Insert sample phone numbers
        PhoneNumber phoneNumber1 = phoneNumberService.create("232-055-3310","Home");
        PhoneNumber phoneNumber2 = phoneNumberService.create("987-654-3210","Work");
        phoneNumberRepository.save(phoneNumber1);
        phoneNumberRepository.save(phoneNumber2);

        // Insert sample contacts with associated phone numbers
        Contact contact1 = contactService.create("John Doe", "john@example.com", phoneNumber1.getPhoneNumber(),phoneNumber1.getType(),"Texas Street");
        Contact contact2 = contactService.create("Timmy Doe", "timmy@example.com", "333-289-4301","Home","Nebraska Street");
        Contact contact3 = contactService.create("Jack Black", "jack@example.com", "222-710-3210","Home","London Street");
        Contact contact4 = contactService.create("Tom Wind", "tom@example.com", "634-880-2304","Home","Utah Street");
        Contact contact5 = contactService.create("Redd White", "redd@example.com", "882-323-8501","Work","Mexico Street");
        Contact contact6 = contactService.create("Orange Black", "orange@example.com", phoneNumber2.getPhoneNumber(),phoneNumber2.getType(),"Fine Street");
        Contact contact7 = contactService.create("Tifany Complex", "tifanny@example.com", "999-320-3230","Work","Nepal Street");
        Contact contact8 = contactService.create("Jessica Andrage", "jessica@example.com", "120-583-4401","Home","Rome Street");
        Contact contact9 = contactService.create("Justin Gaethje", "justin@example.com", "010-858-9902","Home","Georgia Street");
        Contact contact10 = contactService.create("Jimmy Hoffa", "happy@example.com", "088-210-4893","Work","Trifeca Street");
        contactRepository.save(contact1);
        contactRepository.save(contact2);
        contactRepository.save(contact3);
        contactRepository.save(contact4);
        contactRepository.save(contact5);
        contactRepository.save(contact6);
        contactRepository.save(contact7);
        contactRepository.save(contact8);
        contactRepository.save(contact9);
        contactRepository.save(contact10);

    }
}
