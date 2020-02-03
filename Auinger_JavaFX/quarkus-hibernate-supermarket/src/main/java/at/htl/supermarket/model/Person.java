package at.htl.supermarket.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
/*@NamedQueries({
        @NamedQuery(name = "Person.getAll", query = "select p from Person p"),
        @NamedQuery(name = "Person.getByLastname", query = "select p from Person p where p.lastname = :name")
})*/
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    private String mobilePhone;
    private String email;

    public Person() {
    }

    public Person(String firstname, String lastname, LocalDate birthdate, String mobilePhone, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.mobilePhone = mobilePhone;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}