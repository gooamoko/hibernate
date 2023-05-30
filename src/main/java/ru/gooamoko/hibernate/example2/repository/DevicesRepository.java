package ru.gooamoko.hibernate.example2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gooamoko.hibernate.example2.entity.Device;

public interface DevicesRepository extends JpaRepository<Device, Long> {
}
