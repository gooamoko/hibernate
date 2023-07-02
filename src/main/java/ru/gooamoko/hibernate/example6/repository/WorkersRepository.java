package ru.gooamoko.hibernate.example6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.gooamoko.hibernate.example6.entity.WorkerEntity;

import java.util.UUID;

public interface WorkersRepository extends JpaRepository<WorkerEntity, UUID>, JpaSpecificationExecutor<WorkerEntity> {
}
