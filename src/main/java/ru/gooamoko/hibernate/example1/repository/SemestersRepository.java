package ru.gooamoko.hibernate.example1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.gooamoko.hibernate.example1.entity.Group;
import ru.gooamoko.hibernate.example1.entity.Semester;

public interface SemestersRepository extends JpaRepository<Semester, Long> {

    @Query("select sm from Semester sm where sm.group = :grp")
    Page<Semester> getSemestersBatchForGroup(@Param("grp")Group group, Pageable pageRequest);
}
