package io.github.devalan87.locate.service;

import io.github.devalan87.locate.domain.entity.City;
import io.github.devalan87.locate.domain.repository.CityRepository;
import static io.github.devalan87.locate.domain.repository.specs.CitySpecs.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CityService {
    private CityRepository repository;

    public CityService(CityRepository repository) {
        this.repository = repository;
    }

    public void listCities() {
        repository.findAll().forEach(System.out::println);
    }
    public void listCitiesByName() {
        repository.findByNameLike("%AO%", Sort.by("population", "name"))
                .forEach(System.out::println);
    }

    public void listCitiesByNameSql() {
        repository.findByNameNativeSql("Sao Paulo")
                .stream().map(projection ->
                        new City(projection.getId(),
                                projection.getName(),
                                null))
                .forEach(System.out::println);
    }

    public void listCitiesByNamePaging() {
        Pageable pageable = PageRequest.of(0, 3);
        repository.findByNameLike("%a%", pageable)
                .forEach(System.out::println);
    }
    public void listCitiesByNameStartingWith() {
        repository.findByNameStartingWith("Porto").forEach(System.out::println);
    }
    public void listCitiesByNameEndingWith() {
        repository.findByNameEndingWith("a").forEach(System.out::println);
    }

    public void listCitiesByNameContaining() {
        repository.findByNameContaining("a").forEach(System.out::println);
    }

    public void listCitiesByPopulation() {
        repository.findByPopulation(600000l).forEach(System.out::println);
    }

    public void listCitiesByPopulationLessThan() {
        repository.findByPopulationLessThanEqual(600000l).forEach(System.out::println);
    }

    public void listCitiesByPopulationGreaterThan() {
        repository.findByPopulationGreaterThan(600000l).forEach(System.out::println);
    }

    public void ListCitiesByPopulationLessThanAndNameLike() {
        repository.findByPopulationLessThanEqualAndNameLike(1000000000000l, "Sa%");
    }

    public List<City> dynamicFilter(City city) {
        ExampleMatcher matcher =
                            ExampleMatcher
                                    .matching()
                                    .withIgnoreCase()
                                    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<City> example = Example.of(city, matcher);
        return repository.findAll(example);
    }

    public void listCitiesByNameSpec() {
        repository.findAll(
                nameEqual("Sao Paulo")
                        .or(populationGreaterThan(1000l))
        ).forEach(System.out::println);
    }

    public void listCitiesSpecsDynamicFilters(City filter) {
        Specification<City> specs
                = Specification.where((root, query, criteriaBuilder) ->
                    criteriaBuilder.conjunction());

        if(filter.getId() != null)
            specs = specs.and(idEqual(filter.getId()));

        if(StringUtils.hasText(filter.getName()))
            specs = specs.and(nameLike(filter.getName()));

        if(filter.getPopulation() != null)
            specs = specs.and(populationGreaterThan(filter.getPopulation()));

        repository.findAll(specs).forEach(System.out::println);
    }
}
