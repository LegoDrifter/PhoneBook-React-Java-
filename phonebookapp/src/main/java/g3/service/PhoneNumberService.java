package g3.service;

import g3.model.PhoneNumber;

import java.util.List;
import java.util.Optional;

public interface PhoneNumberService {

    List<PhoneNumber> findAll();
    PhoneNumber findById(Long id);

    Optional<PhoneNumber> findByNumber(String number);

    String getNumberById(Long id);

    String getTypeById(Long id);

    PhoneNumber updateNumber(Long id, String newNumber);

    PhoneNumber updateType(Long id,String newType);

    PhoneNumber create(String number, String type);


    PhoneNumber update(Long id, String number, String type);

    PhoneNumber deleteById(Long id);

}
