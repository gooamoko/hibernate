package ru.gooamoko.hibernate.example6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import ru.gooamoko.hibernate.example6.Summary;
import ru.gooamoko.hibernate.example6.entity.WorkerEntity;

import java.util.UUID;

public interface WorkersRepository extends JpaRepository<WorkerEntity, UUID>, JpaSpecificationExecutor<WorkerEntity> {

    @Query(value = "select count(1) as recordsCount, sum(salary) as salarySum from workers", nativeQuery = true)
    Summary getWorkersSummary();
}
