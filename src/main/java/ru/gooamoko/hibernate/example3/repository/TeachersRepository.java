package ru.gooamoko.hibernate.example3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gooamoko.hibernate.example3.entity.Teacher;

public interface TeachersRepository extends JpaRepository<Teacher, Long> {
}
