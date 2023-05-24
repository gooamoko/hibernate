package ru.gooamoko.hibernate.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "STUDENTS")
public class Student {
    @Id
    @Column(name = "STD_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "STD_FIRST_NAME", nullable = false, length = 50)
    private String firstName;

    @Column(name = "STD_MIDDLE_NAME", nullable = false, length = 50)
    private String middleName;

    @Column(name = "STD_LAST_NAME", nullable = false, length = 50)
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "STD_GRP_ID")
    private Group group;

    @Column(name = "STD_CREATE_TS", nullable = false)
    private LocalDateTime createTimestamp = LocalDateTime.now();

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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public LocalDateTime getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(LocalDateTime createTimestamp) {
        this.createTimestamp = createTimestamp;
    }
}
