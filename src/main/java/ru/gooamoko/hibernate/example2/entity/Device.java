package ru.gooamoko.hibernate.example2.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "DEVICES")
public class Device extends EntityWithNameAndDescription {

    @ManyToOne
    @JoinColumn(name = "VENDOR_ID")
    private Vendor vendor;

    @Column(name = "MODEL", nullable = false, length = 100)
    private String model;

    @Column(name = "DAMAGED", nullable = false)
    private boolean damaged;

    @Column(name = "INVENTORY_NUMBER", nullable = false, length = 100)
    private String inventoryNumber;


    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isDamaged() {
        return damaged;
    }

    public void setDamaged(boolean damaged) {
        this.damaged = damaged;
    }

    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(String inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }
}
