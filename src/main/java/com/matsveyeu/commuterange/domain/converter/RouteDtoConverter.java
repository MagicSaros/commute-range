package com.matsveyeu.commuterange.domain.converter;

import com.matsveyeu.commuterange.domain.dto.RouteDto;
import com.matsveyeu.commuterange.domain.city.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RouteDtoConverter {

    @Autowired
    private CityDtoConverter cityDtoConverter;

    public Route fromDtoToEntity(final RouteDto dto) {
        if (dto == null) {
            return null;
        }

        Route route = new Route();
        route.setId(dto.getId());
        route.setFirstEndpoint(cityDtoConverter.fromDtoToEntity(dto.getFirstEndpoint()));
        route.setSecondEndpoint(cityDtoConverter.fromDtoToEntity(dto.getSecondEndpoint()));
        route.setTravelTime(dto.getTravelTime());
        return route;
    }

    public RouteDto fromEntityToDto(final Route route) {
        if (route == null) {
            return null;
        }

        RouteDto dto = new RouteDto();
        dto.setId(dto.getId());
        dto.setFirstEndpoint(cityDtoConverter.fromEntityToDto(route.getFirstEndpoint()));
        dto.setSecondEndpoint(cityDtoConverter.fromEntityToDto(route.getSecondEndpoint()));
        dto.setTravelTime(route.getTravelTime());
        return dto;
    }
}
