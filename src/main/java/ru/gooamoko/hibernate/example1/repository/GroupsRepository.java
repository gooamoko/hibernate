package ru.gooamoko.hibernate.example1.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.gooamoko.hibernate.example1.entity.Group;
import ru.gooamoko.hibernate.example1.entity.Speciality;

import java.util.List;

public interface GroupsRepository extends JpaRepository<Group, Long> {

    @EntityGraph(value = "groups-full", type = EntityGraph.EntityGraphType.LOAD)
    @Query("select g from Group g where g.speciality = :spc")
    List<Group> getBySpeciality(@Param("spc")Speciality speciality);
}
