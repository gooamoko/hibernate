package ru.gooamoko.hibernate.service;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.gooamoko.hibernate.entity.Group;
import ru.gooamoko.hibernate.entity.Speciality;
import ru.gooamoko.hibernate.entity.Student;
import ru.gooamoko.hibernate.repository.GroupsRepository;
import ru.gooamoko.hibernate.repository.SpecialitiesRepository;
import ru.gooamoko.hibernate.repository.StudentsRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DatabaseServiceImpl implements DatabaseService {
    private final EntityManager entityManager;
    private final SpecialitiesRepository specialitiesRepository;
    private final GroupsRepository groupsRepository;
    private final StudentsRepository studentsRepository;

    public DatabaseServiceImpl(EntityManager entityManager, SpecialitiesRepository specialitiesRepository,
                               GroupsRepository groupsRepository, StudentsRepository studentsRepository) {
        this.entityManager = entityManager;
        this.specialitiesRepository = specialitiesRepository;
        this.groupsRepository = groupsRepository;
        this.studentsRepository = studentsRepository;
    }

    @Override
    public Optional<Speciality> getSpecialityWithChildren(Long id) {
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("specialities-with-groups");
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.loadgraph", entityGraph);
        Speciality speciality = entityManager.find(Speciality.class, id, properties);
        return Optional.ofNullable(speciality);
    }

    @Override
    public List<Group> getSpecialityGroups(Speciality speciality) {
        return groupsRepository.getBySpeciality(speciality);
    }

    @Override
    public Speciality saveSpeciality(Speciality speciality) {
        return specialitiesRepository.save(speciality);
    }

    @Override
    public Group saveGroup(Group group) {
        return groupsRepository.save(group);
    }

    @Override
    public Student saveStudent(Student student) {
        return studentsRepository.save(student);
    }

    @Override
    public List<Student> getBatchOfStudents(Group group, int batchSize) {
        Page<Student> batch = studentsRepository.getStudentsBatchForGroup(group, PageRequest.of(0, batchSize));
        return batch.getContent();
    }
}
