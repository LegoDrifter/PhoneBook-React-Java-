package g3.repository;

import g3.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {

    Optional<Contact> findByName(String name);
}
