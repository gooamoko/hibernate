package ru.gooamoko.hibernate.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.gooamoko.hibernate.entity.Group;
import ru.gooamoko.hibernate.entity.Speciality;
import ru.gooamoko.hibernate.entity.Semester;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DatabaseServiceImplTest {
    private final DatabaseService databaseService;

    @Autowired
    public DatabaseServiceImplTest(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @Test
    public void testSaveSpeciality() {
        Speciality speciality = new Speciality();
        speciality.setCode("2030115");
        speciality.setName("Программное обеспечение вычислительной техники и автоматизированных систем");
        speciality = databaseService.saveSpeciality(speciality);
        assertNotNull(speciality.getId());

        for (int i = 0; i < 3; i++) {
            Group testGroup = new Group();
            testGroup.setSpeciality(speciality);
            testGroup.setName("ПКС-2" + i);
            testGroup = databaseService.saveGroup(testGroup);
            assertNotNull(testGroup.getId());

            for (int j = 0; j < 3; j++) {
                Semester testSemester = new Semester();
                testSemester.setGroup(testGroup);
                testSemester.setYear(2020 + i);
                testSemester.setNumber(j);
                testSemester = databaseService.saveSemester(testSemester);
                assertNotNull(testSemester.getId());
            }
        }

        // А теперь попробуем выбрать специальность.
        Optional<Speciality> optionalSpeciality = databaseService.getSpecialityWithChildren(speciality.getId());
        assertTrue(optionalSpeciality.isPresent());
        Speciality newSpeciality = optionalSpeciality.get();
        List<Group> groups = newSpeciality.getGroups();
        assertNotNull(groups);
        assertEquals(3, groups.size());

        // А теперь выберем группы для какой-то специальности.
        List<Group> specialityGroups = databaseService.getSpecialityGroups(speciality);
        assertNotNull(specialityGroups);
        assertEquals(3, specialityGroups.size());

        Group group = specialityGroups.get(0);
        int count = 1;
        List<Semester> semesters = databaseService.getSemesters(group, count);
        assertNotNull(semesters);
        assertEquals(count, semesters.size());
    }
}