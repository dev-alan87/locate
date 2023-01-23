package io.github.devalan87.locate.domain.repository.specs;

import io.github.devalan87.locate.domain.entity.City;
import org.springframework.data.jpa.domain.Specification;

public abstract class CitySpecs {

    public static  Specification<City> idEqual(long id) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("id"), id);
    }
    public static Specification<City> nameEqual(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("name"), name);
    }

    public static Specification<City> populationGreaterThan(Long value) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("population"), value);
    }

    public static Specification<City> propertyEqual(String property, Object value) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(property), value);
    }

    public static Specification<City> populationBetween(Long min, Long max) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("population"), min, max);
    }

    public static Specification<City> nameLike(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.upper(root.get("name")),
                        ("%" + name + "%").toUpperCase()
                );
    }

}
