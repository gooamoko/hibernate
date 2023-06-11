package ru.gooamoko.hibernate.example4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gooamoko.hibernate.example4.entity.Truck;

import java.util.UUID;

public interface TruckRepository extends JpaRepository<Truck, UUID> {
}
