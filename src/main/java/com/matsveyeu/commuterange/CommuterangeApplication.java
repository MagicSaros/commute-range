package com.matsveyeu.commuterange;

import com.matsveyeu.commuterange.domain.graph.Graph;
import com.matsveyeu.commuterange.service.CityService;
import com.matsveyeu.commuterange.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommuterangeApplication implements CommandLineRunner {

    @Autowired
    private CityService cityService;

    @Autowired
    private RouteService routeService;

    public static void main(String[] args) {
        SpringApplication.run(CommuterangeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Graph graph = new Graph();
        cityService
                .getAll()
                .forEach(city -> graph.addNode(city.getName()));

        routeService
                .getAll()
                .forEach(route -> graph.addEdge(route.getFirstEndpoint().getName(), route.getSecondEndpoint().getName(), route.getTravelTime()));

        for (String value : graph.getValues()) {
            System.out.println("Node: " + value);
            graph
                    .getLinkedValuesWithWeights(value)
                    .forEach((linkedValue, weight) ->
                            System.out.println("Linked node: " + linkedValue + ", weight: " + weight));
            System.out.println("--------------------------------------------------------");
        }

        graph
                .getReachableValues("Washington", 250)
                .forEach(city -> System.out.println("You can travel to " + city));
    }
}
