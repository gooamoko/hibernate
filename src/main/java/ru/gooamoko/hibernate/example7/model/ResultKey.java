package ru.gooamoko.hibernate.example7.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class ResultKey implements Serializable {
    private UUID messageId;
    private UUID personId;
    private OperationType operation;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultKey resultKey = (ResultKey) o;
        return Objects.equals(messageId, resultKey.messageId) && Objects.equals(personId, resultKey.personId) && operation == resultKey.operation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId, personId, operation);
    }
}
