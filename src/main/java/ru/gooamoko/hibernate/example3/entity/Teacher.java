package ru.gooamoko.hibernate.example3.entity;

import jakarta.persistence.*;
import ru.gooamoko.hibernate.example3.TeacherCategory;

import java.time.LocalDate;

@Entity
@Table(name = "TEACHERS")
public class Teacher extends Human {
    @Temporal(TemporalType.DATE)
    @Column(name = "WORK_BEGIN", nullable = false)
    private LocalDate workBegin;

    @Enumerated(EnumType.STRING)
    @Column(name = "CATEGORY", nullable = false, length = 100)
    private TeacherCategory category;


    public LocalDate getWorkBegin() {
        return workBegin;
    }

    public void setWorkBegin(LocalDate workBegin) {
        this.workBegin = workBegin;
    }

    public TeacherCategory getCategory() {
        return category;
    }

    public void setCategory(TeacherCategory category) {
        this.category = category;
    }
}
