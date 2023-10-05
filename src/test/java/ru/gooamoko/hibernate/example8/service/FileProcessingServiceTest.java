package ru.gooamoko.hibernate.example8.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import ru.gooamoko.hibernate.example8.entity.OutputFileEntity;
import ru.gooamoko.hibernate.example8.model.FileStatus;
import ru.gooamoko.hibernate.example8.repository.OutputFileRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class FileProcessingServiceTest {

    @Autowired
    private FileProcessingService fileProcessingService;
    @Autowired
    private OutputFileRepository repository;

    @TestConfiguration
    static class TestProcessorConfig {

        @Bean
        public FilePostProcessor testPostProcessor() {
            return entity -> {
                throw new IllegalStateException("Test exception");
            };
        }
    }


    @AfterEach
    public void cleanup() {
        repository.deleteAll();
    }


    @Test
    public void testTransactionWillRollback() {
        try {
            String fileName = String.format("%s.zip", UUID.randomUUID());
            OutputFileEntity entity = new OutputFileEntity();
            entity.setCreateDate(LocalDateTime.now());
            entity.setFileName(fileName);
            entity.setPersonCount(1024);
            entity.setStatus(FileStatus.SENT_OK);

            fileProcessingService.processOutputFile(entity);
        } catch (Exception e) {
            // Ничего страшного.
        }


        List<OutputFileEntity> entities = repository.findAll();
        assertTrue(entities.isEmpty()); // Ожидаем что файлов нет, потому что транзакция откатилась
    }
}