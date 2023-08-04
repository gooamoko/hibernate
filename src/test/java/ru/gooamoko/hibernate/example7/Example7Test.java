package ru.gooamoko.hibernate.example7;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ru.gooamoko.hibernate.example7.entity.ResultEntity;
import ru.gooamoko.hibernate.example7.model.OperationType;
import ru.gooamoko.hibernate.example7.repository.ResultRepository;
import ru.gooamoko.hibernate.specification.GenericSpecification;
import ru.gooamoko.hibernate.specification.model.CriteriaModel;
import ru.gooamoko.hibernate.specification.model.Operation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class Example7Test {
    private final ResultRepository repository;

    @Autowired
    public Example7Test(ResultRepository repository) {
        this.repository = repository;
    }

    @AfterEach
    public void cleanup() {
        repository.deleteAll();
    }

    @Test
    public void testSelectPage() {
        Set<UUID> messageIds = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            messageIds.add(UUID.randomUUID());
        }
        fillTable(messageIds);

        int pageSize = 10; // Количество записей
        Pageable pageRequest = PageRequest.of(0, pageSize);
        Page<ResultEntity> entities = repository.findAll(pageRequest);
        assertNotNull(entities);
        assertEquals(pageSize, entities.getContent().size());
    }

    @Test
    public void testSelectPageWithFilter() {
        Set<UUID> messageIds = new HashSet<>();
        UUID lastId = null;
        for (int i = 0; i < 3; i++) {
            lastId = UUID.randomUUID();
            messageIds.add(lastId);
        }
        fillTable(messageIds);

        int pageSize = 30; // Количество записей
        CriteriaModel model = new CriteriaModel("messageId", Operation.EQ, lastId);
        Pageable pageRequest = PageRequest.of(0, pageSize);
        Page<ResultEntity> entities = repository.findAll(new GenericSpecification<>(model, ResultEntity.class), pageRequest);
        assertNotNull(entities);
        assertEquals(10, entities.getContent().size()); // Будет только 10 записей
    }


    private void fillTable(Set<UUID> messageIds) {
        Random rnd = new Random();
        for (UUID messageId : messageIds) {
            LocalDateTime messageTime = LocalDateTime.now();
            for (int person = 0; person < 5; person++) {
                UUID personId = UUID.randomUUID();
                for (OperationType operation : OperationType.values()) {
                    BigDecimal amount = new BigDecimal(500 + rnd.nextInt(10000))
                            .divide(new BigDecimal(2), 2, RoundingMode.HALF_UP);

                    ResultEntity entity = new ResultEntity();
                    entity.setMessageId(messageId);
                    entity.setPersonId(personId);
                    entity.setOperation(operation);
                    entity.setMessageTimestamp(messageTime);
                    entity.setAmount(amount);

                    repository.save(entity);
                }
            }
        }
    }
}
