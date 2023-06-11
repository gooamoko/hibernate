package ru.gooamoko.hibernate.example3.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "STUDENTS")
public class Student extends Human {

    @ManyToOne
    @JoinColumn(name = "DELEGATE_ID", nullable = false)
    private Human delegate; // Законный представитель

    public Human getDelegate() {
        return delegate;
    }

    public void setDelegate(Human delegate) {
        this.delegate = delegate;
    }
}
