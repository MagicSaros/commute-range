package com.matsveyeu.commuterange.domain.city;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "city")
public class City {

    private Long id;
    private String name;
    private Set<Route> firstRoutes;
    private Set<Route> secondRoutes;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "firstEndpoint", orphanRemoval = true)
    public Set<Route> getFirstRoutes() {
        return firstRoutes;
    }

    public void setFirstRoutes(Set<Route> firstRoutes) {
        this.firstRoutes = firstRoutes;
    }

    @OneToMany(mappedBy = "secondEndpoint", orphanRemoval = true)
    public Set<Route> getSecondRoutes() {
        return secondRoutes;
    }

    public void setSecondRoutes(Set<Route> secondRoutes) {
        this.secondRoutes = secondRoutes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        return new EqualsBuilder()
                .append(id, city.id)
                .append(name, city.name)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .toString();
    }
}
