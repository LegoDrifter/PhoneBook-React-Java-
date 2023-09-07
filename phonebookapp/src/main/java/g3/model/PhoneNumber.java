package g3.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PhoneNumber {

    public PhoneNumber() {
    }

    public PhoneNumber(String number, String type) {
        this.phoneNumber = number;
        this.type = type;
    }


    @Id
    @GeneratedValue
    private Long id;

    private String phoneNumber;

    private String type;

    public void setId(Long id) {
        this.id = id;
    }

    public void setPhoneNumber(String number) {
        this.phoneNumber = number;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getType() {
        return type;
    }
}
