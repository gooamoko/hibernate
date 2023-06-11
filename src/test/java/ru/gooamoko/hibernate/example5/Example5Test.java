package ru.gooamoko.hibernate.example5;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.gooamoko.hibernate.example5.entity.CourseWorkSemesterMark;
import ru.gooamoko.hibernate.example5.entity.SemesterMark;
import ru.gooamoko.hibernate.example5.entity.SubjectSemesterMark;
import ru.gooamoko.hibernate.example5.repository.CourseWorkSemesterMarkRepository;
import ru.gooamoko.hibernate.example5.repository.SemesterMarkRepository;
import ru.gooamoko.hibernate.example5.repository.SubjectSemesterMarkRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class Example5Test {

    private final SemesterMarkRepository semesterMarkRepository;
    private final SubjectSemesterMarkRepository subjectSemesterMarkRepository;
    private final CourseWorkSemesterMarkRepository courseWorkSemesterMarkRepository;

    @Autowired
    public Example5Test(SemesterMarkRepository semesterMarkRepository,
                        SubjectSemesterMarkRepository subjectSemesterMarkRepository,
                        CourseWorkSemesterMarkRepository courseWorkSemesterMarkRepository) {
        this.semesterMarkRepository = semesterMarkRepository;
        this.subjectSemesterMarkRepository = subjectSemesterMarkRepository;
        this.courseWorkSemesterMarkRepository = courseWorkSemesterMarkRepository;
    }

    @AfterEach
    public void cleanup() {
        courseWorkSemesterMarkRepository.deleteAll();
        subjectSemesterMarkRepository.deleteAll();
        semesterMarkRepository.deleteAll();
    }


    @Test
    public void testSubjects() {
        List<SubjectSemesterMark> subjectSemesterMarks = subjectSemesterMarkRepository.findAll();
        assertTrue(subjectSemesterMarks.isEmpty());

        SubjectSemesterMark subjectSemesterMark = new SubjectSemesterMark();
        subjectSemesterMark.setMark(5);
        subjectSemesterMark.setSubject("Информационная безопасность");
        subjectSemesterMark = subjectSemesterMarkRepository.save(subjectSemesterMark);
        assertNotNull(subjectSemesterMark.getId());

        List<SemesterMark> marks = semesterMarkRepository.findAll();
        assertEquals(1, marks.size());
    }

    @Test
    public void testCourseWorks() {
        List<CourseWorkSemesterMark> courseWorkSemesterMarks = courseWorkSemesterMarkRepository.findAll();
        assertTrue(courseWorkSemesterMarks.isEmpty());

        CourseWorkSemesterMark courseWorkSemesterMark = new CourseWorkSemesterMark();
        courseWorkSemesterMark.setMark(5);
        courseWorkSemesterMark.setSubject("Корпоративные информационные системы");
        courseWorkSemesterMark.setTheme("Разработка корпоративной информационной системы");
        courseWorkSemesterMark = courseWorkSemesterMarkRepository.save(courseWorkSemesterMark);
        assertNotNull(courseWorkSemesterMark.getId());

        List<SemesterMark> marks = semesterMarkRepository.findAll();
        assertEquals(1, marks.size());
    }

    @Test
    public void testSemesterMarks() {
        CourseWorkSemesterMark courseWorkSemesterMark = new CourseWorkSemesterMark();
        courseWorkSemesterMark.setMark(5);
        courseWorkSemesterMark.setSubject("Корпоративные информационные системы");
        courseWorkSemesterMark.setTheme("Разработка корпоративной информационной системы");
        courseWorkSemesterMarkRepository.save(courseWorkSemesterMark);

        SubjectSemesterMark subjectSemesterMark = new SubjectSemesterMark();
        subjectSemesterMark.setMark(5);
        subjectSemesterMark.setSubject("Информационная безопасность");
        subjectSemesterMarkRepository.save(subjectSemesterMark);

        List<SemesterMark> marks = semesterMarkRepository.findAll();
        assertEquals(2, marks.size());
    }
}
