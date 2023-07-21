package ru.gooamoko.hibernate.example6.model;

/**
 * Класс, который представляет собой модель условия для фильтра.
 */
public class CriteriaModel {
    private String field;
    private Operation operation;
    private String value;

    public CriteriaModel(String field, Operation operation, String value) {
        this.field = field;
        this.operation = operation;
        this.value = value;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
