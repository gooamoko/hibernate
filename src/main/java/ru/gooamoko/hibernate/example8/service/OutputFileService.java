package ru.gooamoko.hibernate.example8.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gooamoko.hibernate.example8.entity.OutputFileEntity;
import ru.gooamoko.hibernate.example8.repository.OutputFileRepository;

@Service
public class OutputFileService {
    private final OutputFileRepository repository;

    public OutputFileService(OutputFileRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public OutputFileEntity saveOutputFile(OutputFileEntity file) {
        return repository.save(file);
    }
}