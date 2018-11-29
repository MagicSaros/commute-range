package com.matsveyeu.commuterange.service.implementation;

import com.matsveyeu.commuterange.domain.city.City;
import com.matsveyeu.commuterange.domain.graph.Graph;
import com.matsveyeu.commuterange.repository.CityRepository;
import com.matsveyeu.commuterange.service.CityService;
import com.matsveyeu.commuterange.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private RouteService routeService;

    @Override
    public Collection<City> getAll() {
        return cityRepository.findAll();
    }

    @Override
    public City findById(Long id) {
        return cityRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("City not found"));
    }

    @Override
    public City findByName(String name) {
        return cityRepository
                .findCityByName(name)
                .orElse(null);
    }

    @Override
    public Collection<City> getByTravelTime(City rootCity, Integer travelTime) {
        Graph graph = new Graph();
        cityRepository
                .findAll()
                .forEach(city -> graph.addNode(city.getName()));

        routeService
                .getAll()
                .forEach(route -> graph.addEdge(
                        route.getFirstEndpoint().getName(),
                        route.getSecondEndpoint().getName(),
                        route.getTravelTime()));

        return graph
                .getReachableValues(rootCity.getName(), travelTime)
                .stream()
                .map(this::findByName)
                .collect(Collectors.toList());

    }
}
