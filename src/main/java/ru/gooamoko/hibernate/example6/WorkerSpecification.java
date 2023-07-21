package ru.gooamoko.hibernate.example6;

import jakarta.persistence.criteria.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import ru.gooamoko.hibernate.example6.entity.WorkerEntity;
import ru.gooamoko.hibernate.example6.model.CriteriaModel;
import ru.gooamoko.hibernate.example6.model.Operation;

import java.time.LocalDateTime;
import java.util.EnumSet;

public class WorkerSpecification implements Specification<WorkerEntity> {
    private static final EnumSet<Operation> NULL_OPERATIONS = EnumSet.of(Operation.NULL, Operation.NOT_NULL);
    private final CriteriaModel criteriaModel;

    public WorkerSpecification(CriteriaModel criteriaModel) {
        checkCriteria(criteriaModel);
        this.criteriaModel = criteriaModel;
    }

    @Override
    public Predicate toPredicate(Root<WorkerEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        checkCriteria(criteriaModel);

        Operation operation = criteriaModel.getOperation();
        Path<Object> expression = root.get(criteriaModel.getField());
        String value = criteriaModel.getValue();
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
                String likeString = "%" + value + "%";
                return criteriaBuilder.like(expression.as(String.class), likeString);
            }
            case GT -> {
                if ("salary".equals(criteriaModel.getField())) {
                    return criteriaBuilder.gt(expression.as(Integer.class), Integer.parseInt(value));
                } else if ("birthDate".equals(criteriaModel.getField())) {
                    return criteriaBuilder.greaterThan(expression.as(LocalDateTime.class), LocalDateTime.parse(value));
                }
            }
            case LT -> {
                if ("salary".equals(criteriaModel.getField())) {
                    return criteriaBuilder.lt(expression.as(Integer.class), Integer.parseInt(value));
                } else if ("birthDate".equals(criteriaModel.getField())) {
                    return criteriaBuilder.lessThan(expression.as(LocalDateTime.class), LocalDateTime.parse(value));
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
}
