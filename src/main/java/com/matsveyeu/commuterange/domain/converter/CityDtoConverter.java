package com.matsveyeu.commuterange.domain.converter;

import com.matsveyeu.commuterange.domain.dto.CityDto;
import com.matsveyeu.commuterange.domain.city.City;
import org.springframework.stereotype.Component;

@Component
public class CityDtoConverter {

    public City fromDtoToEntity(final CityDto dto) {
        if (dto == null) {
            return null;
        }

        City city = new City();
        city.setId(dto.getId());
        city.setName(dto.getName());
        return city;
    }

    public CityDto fromEntityToDto(final City city) {
        if (city == null) {
            return null;
        }

        CityDto dto = new CityDto();
        dto.setId(city.getId());
        dto.setName(city.getName());
        return dto;
    }
}
