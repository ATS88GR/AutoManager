package com.education.projects.cars.manager.carsmanager.repository;

import com.education.projects.cars.manager.carsmanager.entity.Car;
import com.education.projects.cars.manager.carsmanager.entity.CarPage;
import com.education.projects.cars.manager.carsmanager.entity.CarSearchCriteria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class CarCriteriaRepository {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public CarCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Car> findAllWithFilters(CarPage carPage,
                                        CarSearchCriteria carSearchCriteria){
        CriteriaQuery<Car> criteriaQuery = criteriaBuilder.createQuery(Car.class);
        Root<Car> carRoot = criteriaQuery.from(Car.class);
        Predicate predicate = getPredicate(carSearchCriteria, carRoot);
        criteriaQuery.where(predicate);

        setOrder(carPage, criteriaQuery, carRoot);

        TypedQuery<Car> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(carPage.getPageNumber() * carPage.getPageSize());
        typedQuery.setMaxResults(carPage.getPageSize());

        Pageable pageable = getPageable(carPage);

        long carsCount = getCarsCount(predicate);

        return new PageImpl<>(typedQuery.getResultList(), pageable, carsCount);
    }

    private long getCarsCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Car> countRoot = countQuery.from(Car.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }

    private Pageable getPageable(CarPage carPage) {
        Sort sort = Sort.by(carPage.getSortDirection(), carPage.getSortBy());
        return PageRequest.of(carPage.getPageNumber(),carPage.getPageSize(), sort);
    }

    private void setOrder(CarPage carPage,
                          CriteriaQuery<Car> criteriaQuery,
                          Root<Car> carRoot) {
        if(carPage.getSortDirection().equals(Sort.Direction.ASC)){
            criteriaQuery.orderBy(criteriaBuilder.asc(carRoot.get(carPage.getSortBy())));
        } else criteriaQuery.orderBy(criteriaBuilder.desc(carRoot.get(carPage.getSortBy())));
    }

    private Predicate getPredicate(CarSearchCriteria carSearchCriteria,
                                   Root<Car> carRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if(Objects.nonNull(carSearchCriteria.getBrand()))
            predicates.add(criteriaBuilder.like(carRoot.get("brand"),
                    "%" + carSearchCriteria.getBrand() + "%"));
        if(Objects.nonNull(carSearchCriteria.getModel()))
            predicates.add(criteriaBuilder.like(carRoot.get("model"),
                    "%" + carSearchCriteria.getModel() + "%"));
        if(Objects.nonNull(carSearchCriteria.getYear()))
            predicates.add(criteriaBuilder.like(carRoot.get("year"),
                    String.valueOf(carSearchCriteria.getYear())));
        if(Objects.nonNull(carSearchCriteria.getCost()))
            predicates.add(criteriaBuilder.like(carRoot.get("cost"),
                    String.valueOf(carSearchCriteria.getCost())));
        return criteriaBuilder.and(predicates.toArray((new Predicate[0])));
    }
}
