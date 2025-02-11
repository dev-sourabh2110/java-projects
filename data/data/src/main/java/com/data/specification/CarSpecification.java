package com.data.specification;

import com.data.entity.CarEntity;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class CarSpecification implements Specification<CarEntity> {

    private final String filterField;
    private final String filterValue;

    public CarSpecification(String filterField, String filterValue) {
        this.filterField = filterField;
        this.filterValue = filterValue;
    }

    @Override
    public Predicate toPredicate(Root<CarEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        if ("type".equalsIgnoreCase(filterField)) {
            return cb.equal(root.get("type"), filterValue);
        } else if ("make".equalsIgnoreCase(filterField)) {
            return cb.equal(root.get("make"), filterValue);
        }
        // You can add more conditions for other fields as needed
        return cb.conjunction();
    }
}
