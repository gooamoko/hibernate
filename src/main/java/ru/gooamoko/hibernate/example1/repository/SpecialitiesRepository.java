package ru.gooamoko.hibernate.example1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gooamoko.hibernate.example1.entity.Speciality;

public interface SpecialitiesRepository extends JpaRepository<Speciality, Long> {
}
