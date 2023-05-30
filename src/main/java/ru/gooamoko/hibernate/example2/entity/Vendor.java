package ru.gooamoko.hibernate.example2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "VENDORS")
public class Vendor extends EntityWithName {

    @Column(name = "SITE", length = 200)
    private String site;


    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
