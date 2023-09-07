package g3.model;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Contact {

    public Contact() {
    }

    public Contact(String name, String email, List<PhoneNumber> phoneNumberList, String address) {
        this.name = name;
        this.email = email;
        this.phoneNumberList = phoneNumberList;
        Address = address;
    }

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String email;

    @OneToMany
    private List<PhoneNumber> phoneNumberList;

    private String Address;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumberList(List<PhoneNumber> phoneNumberList) {
        this.phoneNumberList = phoneNumberList;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<PhoneNumber> getPhoneNumberList() {
        return phoneNumberList;
    }

    public String getAddress() {
        return Address;
    }
}
