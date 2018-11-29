package com.matsveyeu.commuterange.domain.graph;

import com.matsveyeu.commuterange.domain.graph.core.GraphCore;
import com.matsveyeu.commuterange.domain.graph.core.Node;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Graph {

    private GraphCore graphCore;

    public Graph() {
        graphCore = new GraphCore();
    }

    public void addNode(String value) {
        Node node = new Node(value);
        graphCore.addNode(node);
    }

    public void linkNode(String existentValue, String newValue, Integer weight) {
        Node existentNode = graphCore.getNodeReference(new Node(existentValue));
        graphCore.linkNode(existentNode, new Node(newValue), weight);
    }

    public void addEdge(String existentValue1, String existentValue2, Integer weight) {
        Node existentNode1 = graphCore.getNodeReference(new Node(existentValue1));
        Node existentNode2 = graphCore.getNodeReference(new Node(existentValue2));
        graphCore.addEdge(existentNode1, existentNode2, weight);
    }

    public List<String> getValues() {
        return graphCore
                .getNodes()
                .stream()
                .map(Node::getValue)
                .collect(Collectors.toList());
    }

    public Set<String> getLinkedValues(final String value) {
        return graphCore
                .getNodes()
                .stream()
                .filter(node -> node.getValue().equals(value))
                .distinct()
                .collect(Collectors.toList())
                .get(0)
                .getLinkedNodes()
                .keySet()
                .stream()
                .map(Node::getValue)
                .collect(Collectors.toSet());
    }

    public Map<String, Integer> getLinkedValuesWithWeights(final String value) {
        final Map<String, Integer> linkedValuesWithWeights = new HashMap<>();
        graphCore
                .getNodes()
                .stream()
                .filter(node -> node.getValue().equals(value))
                .distinct()
                .collect(Collectors.toList())
                .get(0)
                .getLinkedNodes()
                .forEach((node, weight) -> linkedValuesWithWeights.put(node.getValue(), weight));
        return linkedValuesWithWeights;
    }

    public List<String> getReachableValues(String root, Integer capacity) {
        Node node = graphCore.getNodeReference(new Node(root));
        return graphCore
                .getReachableNodes(node, capacity)
                .stream()
                .map(Node::getValue)
                .collect(Collectors.toList());
    }
}
