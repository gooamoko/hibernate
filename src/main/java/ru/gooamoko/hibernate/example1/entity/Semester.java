package ru.gooamoko.hibernate.example1.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "SEMESTERS")
public class Semester {
    @Id
    @Column(name = "SEM_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SEM_YEAR", nullable = false)
    private Integer year;

    @Column(name = "SEM_NUMBER", nullable = false)
    private Integer number;

    @ManyToOne
    @JoinColumn(name = "SEM_GRP_ID")
    private Group group;

    @Column(name = "SEM_CREATE_TS", nullable = false)
    private LocalDateTime createTimestamp = LocalDateTime.now();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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
