package ru.gooamoko.hibernate.example1.service;

import ru.gooamoko.hibernate.example1.entity.Group;
import ru.gooamoko.hibernate.example1.entity.Semester;
import ru.gooamoko.hibernate.example1.entity.Speciality;

import java.util.List;
import java.util.Optional;

public interface DatabaseService {

    default Optional<Speciality> getSpecialityWithChildren(Long id) {
        return Optional.empty();
    }

    Speciality saveSpeciality(Speciality speciality);

    Group saveGroup(Group group);

    Semester saveSemester(Semester semester);

    List<Group> getSpecialityGroups(Speciality speciality);

    List<Semester> getSemesters(Group group, int count);
}
