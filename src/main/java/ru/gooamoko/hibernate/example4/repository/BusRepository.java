package ru.gooamoko.hibernate.example4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gooamoko.hibernate.example4.entity.Bus;

import java.util.UUID;

public interface BusRepository extends JpaRepository<Bus, UUID> {
}
