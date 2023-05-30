package ru.gooamoko.hibernate.example1.service;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.gooamoko.hibernate.example1.entity.Group;
import ru.gooamoko.hibernate.example1.entity.Semester;
import ru.gooamoko.hibernate.example1.entity.Speciality;
import ru.gooamoko.hibernate.example1.repository.GroupsRepository;
import ru.gooamoko.hibernate.example1.repository.SemestersRepository;
import ru.gooamoko.hibernate.example1.repository.SpecialitiesRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DatabaseServiceImpl implements DatabaseService {
    private final EntityManager entityManager;
    private final SpecialitiesRepository specialitiesRepository;
    private final GroupsRepository groupsRepository;
    private final SemestersRepository semestersRepository;

    public DatabaseServiceImpl(EntityManager entityManager, SpecialitiesRepository specialitiesRepository,
                               GroupsRepository groupsRepository, SemestersRepository semestersRepository) {
        this.entityManager = entityManager;
        this.specialitiesRepository = specialitiesRepository;
        this.groupsRepository = groupsRepository;
        this.semestersRepository = semestersRepository;
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
    public Semester saveSemester(Semester semester) {
        return semestersRepository.save(semester);
    }

    @Override
    public List<Semester> getSemesters(Group group, int count) {
        Page<Semester> batch = semestersRepository.getSemestersBatchForGroup(group, PageRequest.of(0, count));
        return batch.getContent();
    }
}
