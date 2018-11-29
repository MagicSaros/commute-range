package com.matsveyeu.commuterange.domain.graph.core;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Node {

    private String value;
    private Map<Node, Integer> linkedNodes;

    public Node(String value) {
        this.value = value;
        linkedNodes = new HashMap<>();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Map<Node, Integer> getLinkedNodes() {
        return linkedNodes;
    }

    public void linkNode(Node node, Integer weight) {
        linkedNodes.put(node, weight);
    }

    public void unlinkNode(Node node) {
        linkedNodes.remove(node);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return new EqualsBuilder()
                .append(value, node.value)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(value)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("value", value)
                .append("linkedNodes", linkedNodes.keySet().stream().map(Node::getValue).collect(Collectors.toList()))
                .toString();
    }
}
