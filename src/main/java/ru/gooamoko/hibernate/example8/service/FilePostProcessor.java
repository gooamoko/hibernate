package ru.gooamoko.hibernate.example8.service;

import ru.gooamoko.hibernate.example8.entity.OutputFileEntity;

public interface FilePostProcessor {

    void process(OutputFileEntity entity) throws Exception;
}
