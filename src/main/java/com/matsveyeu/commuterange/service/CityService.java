package com.matsveyeu.commuterange.service;

import com.matsveyeu.commuterange.domain.city.City;

import java.util.Collection;

public interface CityService {

    Collection<City> getAll();

    City findById(Long id);

    Collection<City> getByTravelTime(City city, Integer travelTime);

    City findByName(String name);
}
