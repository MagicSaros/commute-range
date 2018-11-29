package com.matsveyeu.commuterange.service;

import com.matsveyeu.commuterange.domain.city.Route;

import java.util.Collection;

public interface RouteService {

    Collection<Route> getAll();

    Route findById(Long id);
}
