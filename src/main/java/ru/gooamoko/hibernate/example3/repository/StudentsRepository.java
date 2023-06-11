package ru.gooamoko.hibernate.example3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gooamoko.hibernate.example3.entity.Student;

public interface StudentsRepository extends JpaRepository<Student, Long> {
}
