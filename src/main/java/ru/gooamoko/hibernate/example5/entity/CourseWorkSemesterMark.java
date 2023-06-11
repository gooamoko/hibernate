package ru.gooamoko.hibernate.example5.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("COURSE_WORK")
public class CourseWorkSemesterMark extends SemesterMark {

    @Column(name = "theme", nullable = false)
    private String theme;

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
