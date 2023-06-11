package ru.gooamoko.hibernate.example3.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "HUMANS")
@Inheritance(strategy = InheritanceType.JOINED)
public class Human {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FIRST_NAME", length = 100, nullable = false)
    private String firstName;

    @Column(name = "MIDDLE_NAME", length = 100, nullable = false)
    private String middleName;

    @Column(name = "LAST_NAME", length = 100, nullable = false)
    private String lastName;

    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTH_DATE", nullable = false)
    private LocalDate birthDate;

    @Column(name = "PASSPORT_SERIA", length = 4, nullable = false)
    private String passportSeria;

    @Column(name = "PASSPORT_NUMBER", length = 6, nullable = false)
    private String passportNumber;

    @Column(name = "PHONE", length = 10, nullable = false)
    private String phone;

    @Column(name = "ADDRESS", length = 500, nullable = false)
    private String address;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassportSeria() {
        return passportSeria;
    }

    public void setPassportSeria(String passportSeria) {
        this.passportSeria = passportSeria;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

