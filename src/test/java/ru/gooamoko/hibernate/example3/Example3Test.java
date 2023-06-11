package ru.gooamoko.hibernate.example3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.gooamoko.hibernate.example3.entity.Human;
import ru.gooamoko.hibernate.example3.entity.Student;
import ru.gooamoko.hibernate.example3.entity.Teacher;
import ru.gooamoko.hibernate.example3.repository.HumansRepository;
import ru.gooamoko.hibernate.example3.repository.StudentsRepository;
import ru.gooamoko.hibernate.example3.repository.TeachersRepository;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class Example3Test {
    private final HumansRepository humansRepository;
    private final TeachersRepository teachersRepository;
    private final StudentsRepository studentsRepository;

    @Autowired
    public Example3Test(HumansRepository humansRepository, TeachersRepository teachersRepository, StudentsRepository studentsRepository) {
        this.humansRepository = humansRepository;
        this.teachersRepository = teachersRepository;
        this.studentsRepository = studentsRepository;
    }

    @AfterEach
    public void cleanup() {
        studentsRepository.deleteAll();
        teachersRepository.deleteAll();
        humansRepository.deleteAll();
    }


    @Test
    public void testRepositories() {
        List<Human> humans = humansRepository.findAll();
        assertNotNull(humans);
        assertTrue(humans.isEmpty());

        Human testHuman = new Human();
        testHuman.setBirthDate(LocalDate.of(1970, 1, 10));
        testHuman.setFirstName("Леопольд");
        testHuman.setMiddleName("Никодимович");
        testHuman.setLastName("Кудасов");
        testHuman.setPassportSeria("3208");
        testHuman.setPassportNumber("487354");
        testHuman.setPhone("9513522607");
        testHuman.setAddress("Test address");
        testHuman = humansRepository.save(testHuman);
        assertNotNull(testHuman.getId());

        List<Teacher> teachers = teachersRepository.findAll();
        assertNotNull(teachers);
        assertTrue(teachers.isEmpty());

        Teacher teacher = new Teacher();
        teacher.setCategory(TeacherCategory.HIGH);
        teacher.setWorkBegin(LocalDate.now());
        teacher.setBirthDate(LocalDate.of(1980, 2, 14));
        teacher.setFirstName("Иван");
        teacher.setMiddleName("Иванoвич");
        teacher.setLastName("Иванoв");
        teacher.setPassportSeria("3202");
        teacher.setPassportNumber("428735");
        teacher.setPhone("9133352260");
        teacher.setAddress("Test address");

        teacher = teachersRepository.save(teacher);
        assertNotNull(teacher.getId());

        List<Student> students = studentsRepository.findAll();
        assertNotNull(students);
        assertTrue(students.isEmpty());

        Student student = new Student();
        student.setDelegate(teacher);
        student.setBirthDate(LocalDate.of(2000, 6, 20));
        student.setFirstName("Николай");
        student.setMiddleName("Иванoвич");
        student.setLastName("Иванoв");
        student.setPassportSeria("3214");
        student.setPassportNumber("873569");
        student.setPhone("9053052603");
        student.setAddress("Test address");

        student = studentsRepository.save(student);
        assertNotNull(student.getId());

        humans = humansRepository.findAll();
        assertNotNull(humans);
        assertEquals(3, humans.size());
    }
}
