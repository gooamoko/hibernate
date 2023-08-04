package ru.gooamoko.hibernate.example7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.gooamoko.hibernate.example7.entity.ResultEntity;
import ru.gooamoko.hibernate.example7.model.ResultKey;

public interface ResultRepository extends JpaRepository<ResultEntity, ResultKey>, JpaSpecificationExecutor<ResultEntity> {
}
