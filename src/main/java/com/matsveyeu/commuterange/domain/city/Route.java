package com.matsveyeu.commuterange.domain.city;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

@Entity
@Table(name = "route")
public class Route {

    private Long id;
    private City firstEndpoint;
    private City secondEndpoint;
    private Integer travelTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "first_city_id")
    public City getFirstEndpoint() {
        return firstEndpoint;
    }

    public void setFirstEndpoint(City firstEndpoint) {
        this.firstEndpoint = firstEndpoint;
    }

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "second_city_id")
    public City getSecondEndpoint() {
        return secondEndpoint;
    }

    public void setSecondEndpoint(City secondEndpoint) {
        this.secondEndpoint = secondEndpoint;
    }

    @Column
    public Integer getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(Integer travelTime) {
        this.travelTime = travelTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Route route = (Route) o;

        return new EqualsBuilder()
                .append(id, route.id)
                .append(firstEndpoint, route.firstEndpoint)
                .append(secondEndpoint, route.secondEndpoint)
                .append(travelTime, route.travelTime)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(firstEndpoint)
                .append(secondEndpoint)
                .append(travelTime)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("firstEndpoint", firstEndpoint)
                .append("secondEndpoint", secondEndpoint)
                .append("travelTime", travelTime)
                .toString();
    }
}
