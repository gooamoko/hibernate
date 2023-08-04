package ru.gooamoko.hibernate.specification;

import jakarta.persistence.criteria.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import ru.gooamoko.hibernate.specification.model.CriteriaModel;
import ru.gooamoko.hibernate.specification.model.Operation;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

public class GenericSpecification<T> implements Specification<T> {
    private static final EnumSet<Operation> NULL_OPERATIONS = EnumSet.of(Operation.NULL, Operation.NOT_NULL);
    private static final Set<String> PRIMITIVE_NUMBERS = Set.of("byte", "short", "int", "long", "float", "double");
    private final CriteriaModel criteriaModel;
    private final Class<T> entityClass;

    public GenericSpecification(CriteriaModel criteriaModel, Class<T> entityClass) {
        checkCriteria(criteriaModel);
        this.criteriaModel = criteriaModel;
        this.entityClass = entityClass;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        checkCriteria(criteriaModel);

        Operation operation = criteriaModel.getOperation();
        String fieldName = criteriaModel.getField();
        Path<Object> expression = root.get(fieldName);
        Object value = criteriaModel.getValue();
        switch (operation) {
            case NULL -> {
                return criteriaBuilder.isNull(expression);
            }
            case NOT_NULL -> {
                return criteriaBuilder.isNotNull(expression);
            }
            case EQ -> {
                return criteriaBuilder.equal(expression, value);
            }
            case LIKE -> {
                if (isString(fieldName)) {
                    String likeString = "%" + value + "%";
                    return criteriaBuilder.like(expression.as(String.class), likeString);
                }
            }
            case GT -> {
                if (isNumber(fieldName)) {
                    return criteriaBuilder.gt(expression.as(BigDecimal.class), new BigDecimal(String.valueOf(value)));
                } else if (isDate(fieldName)) {
                    return criteriaBuilder.greaterThan(expression.as(LocalDateTime.class), toDate(value));
                }
            }
            case LT -> {
                if (isNumber(fieldName)) {
                    return criteriaBuilder.lt(expression.as(BigDecimal.class), new BigDecimal(String.valueOf(value)));
                } else if (isDate(fieldName)) {
                    return criteriaBuilder.lessThan(expression.as(LocalDateTime.class), toDate(value));
                }
            }
        }
        return null;
    }


    private void checkCriteria(CriteriaModel criteriaModel) {
        if (criteriaModel == null) {
            throw new IllegalArgumentException("CriteriaModel is null");
        }

        if (StringUtils.isBlank(criteriaModel.getField())) {
            throw new IllegalArgumentException("Field must be not null!");
        }
        Operation operation = criteriaModel.getOperation();
        if (operation == null) {
            throw new IllegalArgumentException("Operation must be not null!");
        }
        if (!NULL_OPERATIONS.contains(operation) && criteriaModel.getValue() == null) {
            throw new IllegalArgumentException("Value must be not null!");
        }
    }

    private LocalDateTime toDate(Object value) {
        if (value instanceof LocalDateTime) {
            return (LocalDateTime) value;
        } else if (value instanceof LocalDate) {
            return LocalDateTime.of((LocalDate) value, LocalTime.MIN);
        } else if (value instanceof Date) {
            return LocalDateTime.ofEpochSecond(((Date) value).getTime(), 0, ZoneOffset.UTC);
        } else if (value instanceof String) {
            return LocalDateTime.parse((String)value);
        } else {
            throw new RuntimeException("Unsupported date value: " + value);
        }
    }

    private boolean isNumber(String fieldName) {
        Class<?> fieldType = getFieldType(fieldName);
        if (fieldType.isPrimitive()) {
            return PRIMITIVE_NUMBERS.contains(fieldType.getName());
        }
        return Number.class.isAssignableFrom(fieldType);
    }

    private boolean isString(String fieldName) {
        Class<?> fieldType = getFieldType(fieldName);
        return fieldType.isEnum() || String.class.equals(fieldType);
    }

    private boolean isDate(String fieldName) {
        Class<?> fieldType = getFieldType(fieldName);
        if (LocalDate.class.equals(fieldType) || LocalDateTime.class.equals(fieldType)) {
            return true;
        }
        return Date.class.isAssignableFrom(fieldType);
    }

    private Class<?> getFieldType(String fieldName) {
        try {
            Field field = entityClass.getDeclaredField(fieldName);
            return field.getType();
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
}
