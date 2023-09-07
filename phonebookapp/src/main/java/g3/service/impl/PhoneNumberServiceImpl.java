package g3.service.impl;

import g3.model.PhoneNumber;
import g3.model.exceptions.InvalidPhoneNumberException;
import g3.repository.PhoneNumberRepository;
import g3.service.PhoneNumberService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhoneNumberServiceImpl implements PhoneNumberService {
    private final PhoneNumberRepository phoneNumberRepository;

    public PhoneNumberServiceImpl(PhoneNumberRepository phoneNumberRepository) {
        this.phoneNumberRepository = phoneNumberRepository;
    }

    @Override
    public List<PhoneNumber> findAll() {
        return this.phoneNumberRepository.findAll();
    }

    @Override
    public PhoneNumber findById(Long id) {
        return this.phoneNumberRepository.findById(id).orElseThrow(() -> new InvalidPhoneNumberException("Phone number not found with ID: " + id));
    }

    @Override
    public Optional<PhoneNumber> findByNumber(String number) {
        return this.phoneNumberRepository.findByPhoneNumber(number);
    }

    @Override
    public String getNumberById(Long id) {
        PhoneNumber number= phoneNumberRepository.findById(id).orElseThrow(() -> new InvalidPhoneNumberException("Phone number not found with ID: " + id));
        return number.getPhoneNumber();

    }

    @Override
    public String getTypeById(Long id) {
        PhoneNumber number= phoneNumberRepository.findById(id).orElseThrow(() -> new InvalidPhoneNumberException("Phone number not found with ID: " + id));
        return number.getType();
    }

    @Override
    public PhoneNumber updateNumber(Long id, String newNumber) {
        PhoneNumber phoneNumber = phoneNumberRepository.findById(id)
                .orElseThrow(() -> new InvalidPhoneNumberException("Phone number not found with ID: " + id));

        phoneNumber.setPhoneNumber(newNumber);
        return phoneNumberRepository.save(phoneNumber);

    }

    @Override
    public PhoneNumber updateType(Long id, String newType) {
        PhoneNumber phoneNumber = phoneNumberRepository.findById(id)
                .orElseThrow(() -> new InvalidPhoneNumberException("Phone number not found with ID: " + id));

        phoneNumber.setType(newType);
        return phoneNumberRepository.save(phoneNumber);
    }

    // TO DO - MAKE CREATE

    @Override
    public PhoneNumber create(String number, String type) {
        PhoneNumber phoneNumber = new PhoneNumber(number,type);
        this.phoneNumberRepository.save(phoneNumber);
        return phoneNumber;
    }

    @Override
    public PhoneNumber update(Long id, String number, String type) {
        PhoneNumber phoneNumber = this.phoneNumberRepository.findById(id)
                .orElseThrow(() -> new InvalidPhoneNumberException("Phone number not found with ID: " + id));

        phoneNumber.setPhoneNumber(number);
        phoneNumber.setType(type);

        this.phoneNumberRepository.save(phoneNumber);
        return phoneNumber;
    }

    @Override
    public PhoneNumber deleteById(Long id) {
        PhoneNumber phoneNumber = this.phoneNumberRepository.findById(id)
                .orElseThrow(() -> new InvalidPhoneNumberException("Phone number not found with ID: " + id));

        this.phoneNumberRepository.deleteById(id);

        return phoneNumber;
    }
}
