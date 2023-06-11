package ru.gooamoko.hibernate.example5.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("SUBJECT")
public class SubjectSemesterMark extends SemesterMark {
}
