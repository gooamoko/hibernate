package ru.gooamoko.hibernate.example4.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "bus")
public class Bus extends Vehicle {

    @Column(name = "sits_count", nullable = false)
    private Integer sitesCount;

    public Integer getSitesCount() {
        return sitesCount;
    }

    public void setSitesCount(Integer sitesCount) {
        this.sitesCount = sitesCount;
    }
}
