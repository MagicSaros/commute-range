package com.matsveyeu.commuterange.domain.graph.core;

import java.util.*;
import java.util.stream.Collectors;

public class GraphCore {

    private List<Node> nodes;

    public GraphCore() {
        nodes = new ArrayList<>();
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public Node getNodeReference(Node node) {
        if (nodes.contains(node)) {
            return nodes.get(nodes.indexOf(node));
        }

        return null;
    }

    public void linkNode(Node existentNode, Node newNode, Integer weight) {
        Node node = getNodeReference(existentNode);
        addNode(newNode);
        node.linkNode(newNode, weight);
        newNode.linkNode(node, weight);
    }

    public void addEdge(Node existentNodeA, Node existentNodeB, Integer weight) {
        Node nodeA = getNodeReference(existentNodeA);
        Node nodeB = getNodeReference(existentNodeB);
        nodeA.linkNode(nodeB, weight);
        nodeB.linkNode(nodeA, weight);
    }

    public List<Node> getReachableNodes(final Node root, final Integer capacity) {
        Map<Node, Integer> distances = new HashMap<>();
        distances.put(root, 0);

        List<Node> unvisited = new LinkedList<>();
        List<Node> visited = new LinkedList<>();
        unvisited.add(root);

        while (!unvisited.isEmpty()) {
            Node node = unvisited.get(0);
            Map<Node, Integer> linkedNodes = node.getLinkedNodes();
            int currentPath = distances.get(node);
            int currentCapacity = capacity - distances.get(node);
            for (Node linkedNode : linkedNodes.keySet()) {
                int path = linkedNodes.get(linkedNode);
                if (path > currentCapacity) {
                    continue;
                }

                if (!unvisited.contains(linkedNode) && !visited.contains(linkedNode)) {
                    unvisited.add(linkedNode);
                }

                int distance = currentPath + path;

                if (distances.containsKey(linkedNode)) {
                    int currentDistance = distances.get(linkedNode);
                    if (currentDistance > distance) {
                        distances.put(linkedNode, distance);
                    }
                } else {
                    distances.put(linkedNode, distance);
                }
            }
            unvisited.remove(node);
            visited.add(node);
        }

        return distances
                .keySet()
                .stream()
                .filter(node -> distances.get(node) <= capacity && distances.get(node) != 0)
                .collect(Collectors.toList());
    }
}
