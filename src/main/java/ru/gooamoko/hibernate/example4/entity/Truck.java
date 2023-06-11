package ru.gooamoko.hibernate.example4.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "truck")
public class Truck extends Vehicle {

    @Column(name = "max_weight", nullable = false)
    private Integer maxWeight;

    public Integer getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(Integer maxWeight) {
        this.maxWeight = maxWeight;
    }
}
