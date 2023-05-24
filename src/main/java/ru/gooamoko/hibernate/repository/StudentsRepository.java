package ru.gooamoko.hibernate.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.gooamoko.hibernate.entity.Group;
import ru.gooamoko.hibernate.entity.Student;

public interface StudentsRepository extends JpaRepository<Student, Long> {

    @Query("select st from Student st where st.group = :grp")
    Page<Student> getStudentsBatchForGroup(@Param("grp")Group group, Pageable pageRequest);
}
