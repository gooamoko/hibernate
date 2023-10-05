package ru.gooamoko.hibernate.example8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gooamoko.hibernate.example8.entity.OutputFileEntity;

import java.util.UUID;

public interface OutputFileRepository extends JpaRepository<OutputFileEntity, UUID> {
}
