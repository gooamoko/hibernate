package ru.gooamoko.hibernate.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@NamedEntityGraph(
        name = "specialities-with-groups",
        attributeNodes = {
                @NamedAttributeNode(value = "groups", subgraph = "groups-with-students")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "groups-with-students",
                attributeNodes = {
                                @NamedAttributeNode("students")
                })
        }
)
@Entity
@Table(name = "SPECIALITIES")
public class Speciality {
    @Id
    @Column(name = "SPC_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SPC_NAME", nullable = false)
    private String name;

    @Column(name = "SPC_CODE", nullable = false, length = 50)
    private String code;

    @Column(name = "SPC_CREATE_TS", nullable = false)
    private LocalDateTime createTimestamp = LocalDateTime.now();

    @OneToMany(mappedBy = "speciality")
    private List<Group> groups;


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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(LocalDateTime createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
