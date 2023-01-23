package io.github.devalan87.locate.domain.repository;

import io.github.devalan87.locate.domain.entity.City;
import io.github.devalan87.locate.domain.repository.projections.CityProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CityRepository extends
        JpaRepository<City, Long>, JpaSpecificationExecutor<City> {

    List<City> findByName(String name);
    @Query(" select c from City c where upper(c.name) like upper(?1) ")
    List<City> findByNameLike(String name, Sort sort);

    @Query(nativeQuery = true, value = "SELECT c.id, c.name FROM tb_city c WHERE c.name =:name")
    List<CityProjection> findByNameNativeSql(@Param("name") String name);

    @Query(" select c from City c where upper(c.name) like upper(?1) ")
    Page<City> findByNameLike(String name, Pageable pageable);

    List<City> findByNameStartingWith(String name);
    List<City> findByNameEndingWith(String name);
    List<City> findByNameContaining(String name);
    List<City> findByPopulation(Long population);

    List<City> findByPopulationLessThanEqual(Long population);

    List<City> findByPopulationGreaterThan(Long population);

    List<City> findByPopulationLessThanEqualAndNameLike(Long population, String name);

}
