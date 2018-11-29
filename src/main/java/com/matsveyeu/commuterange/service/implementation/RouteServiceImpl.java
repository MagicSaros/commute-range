package com.matsveyeu.commuterange.service.implementation;

import com.matsveyeu.commuterange.domain.city.Route;
import com.matsveyeu.commuterange.repository.RouteRepository;
import com.matsveyeu.commuterange.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Override
    public Collection<Route> getAll() {
        return routeRepository.findAll();
    }

    @Override
    public Route findById(Long id) {
        return routeRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Route not found"));
    }
}
