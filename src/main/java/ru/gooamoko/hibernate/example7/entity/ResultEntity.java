package ru.gooamoko.hibernate.example7.entity;

import jakarta.persistence.*;
import ru.gooamoko.hibernate.example7.model.OperationType;
import ru.gooamoko.hibernate.example7.model.ResultKey;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "results")
@IdClass(ResultKey.class)
public class ResultEntity {

    @Id
    @Column(name = "msg_id", nullable = false)
    private UUID messageId;

    @Id
    @Column(name = "person_id", nullable = false)
    private UUID personId;

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "operation", nullable = false)
    private OperationType operation;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "msg_date", nullable = false)
    private LocalDateTime messageTimestamp;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    public UUID getMessageId() {
        return messageId;
    }

    public void setMessageId(UUID messageId) {
        this.messageId = messageId;
    }

    public UUID getPersonId() {
        return personId;
    }

    public void setPersonId(UUID personId) {
        this.personId = personId;
    }

    public OperationType getOperation() {
        return operation;
    }

    public void setOperation(OperationType operation) {
        this.operation = operation;
    }

    public LocalDateTime getMessageTimestamp() {
        return messageTimestamp;
    }

    public void setMessageTimestamp(LocalDateTime messageTimestamp) {
        this.messageTimestamp = messageTimestamp;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
