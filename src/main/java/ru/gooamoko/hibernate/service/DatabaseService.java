package ru.gooamoko.hibernate.service;

import ru.gooamoko.hibernate.entity.Group;
import ru.gooamoko.hibernate.entity.Speciality;
import ru.gooamoko.hibernate.entity.Semester;

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
