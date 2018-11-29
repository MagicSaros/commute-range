package com.matsveyeu.commuterange.domain.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RouteDto {

    private Long id;
    private CityDto firstEndpoint;
    private CityDto secondEndpoint;
    private Integer travelTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotEmpty
    public CityDto getFirstEndpoint() {
        return firstEndpoint;
    }

    public void setFirstEndpoint(CityDto firstEndpoint) {
        this.firstEndpoint = firstEndpoint;
    }

    @NotEmpty
    public CityDto getSecondEndpoint() {
        return secondEndpoint;
    }

    public void setSecondEndpoint(CityDto secondEndpoint) {
        this.secondEndpoint = secondEndpoint;
    }

    @NotNull
    public Integer getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(Integer travelTime) {
        this.travelTime = travelTime;
    }
}
