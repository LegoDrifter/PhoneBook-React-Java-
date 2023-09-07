package g3.service;

import g3.model.Contact;
import g3.model.ContactDTO;
import g3.model.PhoneNumber;

import java.util.List;
import java.util.Optional;

public interface ContactService {

    List<Contact> findAll();

    Contact findById(Long id);

    Optional<Contact> findByName(String Name);

    Contact create(String name,String email, String phoneNumber, String type,String address);

    Contact create(ContactDTO contactDTO);
    Contact update(Long id,String name,String email,List<Long> phoneNumberIds);

    Contact update(Long id,ContactDTO contactDTO);
    Contact deleteById(Long id);

}
