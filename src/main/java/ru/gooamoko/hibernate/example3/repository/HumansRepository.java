package ru.gooamoko.hibernate.example3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gooamoko.hibernate.example3.entity.Human;

public interface HumansRepository extends JpaRepository<Human, Long> {
}
