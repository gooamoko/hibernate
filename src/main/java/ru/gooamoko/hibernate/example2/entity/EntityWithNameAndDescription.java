package ru.gooamoko.hibernate.example2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class EntityWithNameAndDescription extends EntityWithName {

    @Column(name = "description", length = 500)
    private String description;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
