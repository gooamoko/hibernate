package ru.gooamoko.hibernate.example8.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gooamoko.hibernate.example8.entity.OutputFileEntity;

import java.util.Collection;

@Service
public class FileProcessingService {
    private final OutputFileService outputFileService;
    private final Collection<FilePostProcessor> filePostProcessors;

    public FileProcessingService(OutputFileService outputFileService, Collection<FilePostProcessor> filePostProcessors) {
        this.outputFileService = outputFileService;
        this.filePostProcessors = filePostProcessors;
    }

    @Transactional
    public void processOutputFile(OutputFileEntity file) throws Exception {
        OutputFileEntity savedFile = outputFileService.saveOutputFile(file);
        if (filePostProcessors != null) {
            for (FilePostProcessor postProcessor : filePostProcessors) {
                postProcessor.process(savedFile);
            }
        }
    }
}
