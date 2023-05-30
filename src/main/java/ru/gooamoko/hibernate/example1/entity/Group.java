package ru.gooamoko.hibernate.example1.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@NamedEntityGraph(
        name = "groups-full",
        attributeNodes = {
                @NamedAttributeNode("semesters")
        }
)
@Entity
@Table(name = "GROUPS")
public class Group {

    @Id
    @Column(name = "GRP_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "GRP_NAME", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "GRP_SPC_ID")
    private Speciality speciality;

    @Column(name = "GRP_CREATE_TS", nullable = false)
    private LocalDateTime createTimestamp = LocalDateTime.now();

    @OneToMany(mappedBy = "group")
    private List<Semester> semesters;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public LocalDateTime getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(LocalDateTime createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public List<Semester> getSemesters() {
        return semesters;
    }

    public void setSemesters(List<Semester> semesters) {
        this.semesters = semesters;
    }
}
