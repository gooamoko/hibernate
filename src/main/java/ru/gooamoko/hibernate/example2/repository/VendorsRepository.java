package ru.gooamoko.hibernate.example2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gooamoko.hibernate.example2.entity.Vendor;

public interface VendorsRepository extends JpaRepository<Vendor, Long> {
}
