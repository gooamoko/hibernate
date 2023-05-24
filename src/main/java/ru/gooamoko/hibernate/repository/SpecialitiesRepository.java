package ru.gooamoko.hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gooamoko.hibernate.entity.Speciality;

public interface SpecialitiesRepository extends JpaRepository<Speciality, Long> {
}
