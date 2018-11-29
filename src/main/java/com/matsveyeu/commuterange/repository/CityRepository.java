package com.matsveyeu.commuterange.repository;

import com.matsveyeu.commuterange.domain.city.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.OptionalInt;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    Optional<City> findCityByName(String name);
}
