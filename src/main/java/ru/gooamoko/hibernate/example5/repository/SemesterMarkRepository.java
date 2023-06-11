package ru.gooamoko.hibernate.example5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gooamoko.hibernate.example5.entity.SemesterMark;

import java.util.UUID;

public interface SemesterMarkRepository extends JpaRepository<SemesterMark, UUID> {
}
