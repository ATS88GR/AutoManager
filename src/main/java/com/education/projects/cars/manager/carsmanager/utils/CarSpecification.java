package com.education.projects.cars.manager.carsmanager.utils;
import com.education.projects.cars.manager.carsmanager.entity.Car;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class CarSpecification implements Specification<Car> {
    private SearchCriteria criteria;

    public CarSpecification(SearchCriteria searchCriteria) {
    }

    @Override
    public Predicate toPredicate(Root<Car> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (criteria.getOperation().equalsIgnoreCase("eq")) {
            return builder.equal(root.get(criteria.getKey()), criteria.getValue());
        } else return builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
    }
}
