package ru.gooamoko.hibernate.example6;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ru.gooamoko.hibernate.example6.entity.WorkerEntity;
import ru.gooamoko.hibernate.example6.repository.WorkersRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class Example6Test {
    private final WorkersRepository repository;

    @Autowired
    public Example6Test(WorkersRepository repository) {
        this.repository = repository;
    }

    @BeforeEach
    public void setup() {
        // Добавим данные (10 записей)
        addPerson("Иван", "Иванович", "Иванов", LocalDateTime.of(1985, 2, 12, 12, 0));
        addPerson("Петр", "Иванович", "Тищенко", LocalDateTime.of(1985, 2, 1, 10, 0));
        addPerson("Василий", "Сергеевич", "Пупкин", LocalDateTime.of(1984, 12, 30, 8, 12));
        addPerson("Антуан", "Иванович", "Селианов", LocalDateTime.of(1983, 6, 20, 15, 54));
        addPerson("Клара", "Николаевна", "Пупкина", LocalDateTime.of(1985, 7, 6, 17, 0));
        addPerson("Марианна", "Ивановна", "Зеленкина", LocalDateTime.of(1980, 8, 5, 9, 32));
        addPerson("Вера", "Афонасьевна", "Подольская", LocalDateTime.of(1982, 9, 17, 7, 47));
        addPerson("Никодим", "Всеславьевич", "Гок", LocalDateTime.of(1981, 5, 10, 6, 17));
        addPerson("Просковья", "Ефремовна", "Мальга", LocalDateTime.of(1979, 11, 3, 11, 23));
        addPerson("Мария", "Иванвна", "Ра", LocalDateTime.of(1986, 3, 17, 18, 38));
    }

    @AfterEach
    public void cleanup() {
        repository.deleteAll();
    }


    @Test
    public void testSelectPageWithoutSort() {
        int pageSize = 5; // Количество записей
        Pageable pageRequest = PageRequest.of(0, pageSize);
        Page<WorkerEntity> entities = repository.findAll(pageRequest);
        assertNotNull(entities);
        assertEquals(pageSize, entities.stream().count());
    }

    @Test
    public void testSelectPageSortByLastName() {
        int pageSize = 5; // Количество записей
        Pageable pageRequest = PageRequest.of(0, pageSize, Sort.by(Sort.Direction.ASC, "lastName"));
        Page<WorkerEntity> entities = repository.findAll(pageRequest);
        List<WorkerEntity> workers = entities.toList();
        assertNotNull(workers);
        assertEquals(pageSize, workers.size());
        assertEquals("Гок", workers.get(0).getLastName());
    }

    @Test
    public void testSelectPageSortByBirthDateDesc() {
        int pageSize = 3; // Количество записей
        Pageable pageRequest = PageRequest.of(0, pageSize, Sort.by(Sort.Direction.DESC, "birthDate"));
        Page<WorkerEntity> entities = repository.findAll(pageRequest);
        List<WorkerEntity> workers = entities.toList();
        assertNotNull(workers);
        assertEquals(pageSize, workers.size());
        assertEquals("Ра", workers.get(0).getLastName());
        assertEquals("Пупкина", workers.get(1).getLastName());
        assertEquals("Иванов", workers.get(2).getLastName());
    }


    private void addPerson(String firstName, String middleName, String lastName, LocalDateTime birthDate) {
        WorkerEntity entity = new WorkerEntity();
        entity.setFirstName(firstName);
        entity.setMiddleName(middleName);
        entity.setLastName(lastName);
        entity.setBirthDate(birthDate);

        repository.save(entity);
    }
}
