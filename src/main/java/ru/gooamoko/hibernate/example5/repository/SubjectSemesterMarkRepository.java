package ru.gooamoko.hibernate.example5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gooamoko.hibernate.example5.entity.SubjectSemesterMark;

import java.util.UUID;

public interface SubjectSemesterMarkRepository extends JpaRepository<SubjectSemesterMark, UUID> {
}
