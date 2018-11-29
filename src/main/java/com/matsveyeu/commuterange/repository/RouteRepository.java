package com.matsveyeu.commuterange.repository;

import com.matsveyeu.commuterange.domain.city.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
}
