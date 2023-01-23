package io.github.devalan87.locate;

import io.github.devalan87.locate.domain.entity.City;
import io.github.devalan87.locate.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LocateApplication
		implements CommandLineRunner {

	@Autowired
	private CityService service;

	public static void main(String[] args) {
		SpringApplication.run(LocateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		service.listCitiesSpecsDynamicFilters(
				new City(null,
						"rio de janeiro",
						null)
		);

		service.listCitiesByNameSql();
	}

}
