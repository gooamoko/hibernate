package ru.gooamoko.hibernate.example5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gooamoko.hibernate.example5.entity.CourseWorkSemesterMark;

import java.util.UUID;

public interface CourseWorkSemesterMarkRepository extends JpaRepository<CourseWorkSemesterMark, UUID> {
}
