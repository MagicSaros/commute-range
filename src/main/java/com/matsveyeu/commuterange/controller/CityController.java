package com.matsveyeu.commuterange.controller;

import com.matsveyeu.commuterange.domain.converter.CityDtoConverter;
import com.matsveyeu.commuterange.domain.dto.CityDto;
import com.matsveyeu.commuterange.domain.city.City;
import com.matsveyeu.commuterange.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @Autowired
    private CityDtoConverter cityDtoConverter;

    @GetMapping
    public ResponseEntity<?> getAllCities() {
        Collection<CityDto> citiesDto = cityService
                .getAll()
                .stream()
                .map(cityDtoConverter::fromEntityToDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(citiesDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCityById(@PathVariable Long id) {
        City city = cityService.findById(id);
        CityDto dto = cityDtoConverter.fromEntityToDto(city);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/{id}/neighbours")
    public ResponseEntity<?> getCitiesByTravelTime(@PathVariable Long id, @RequestParam( value = "travel_time", defaultValue = "0") Integer travelTime) {
        City city = cityService.findById(id);
        Collection<CityDto> citiesDto = cityService
                .getByTravelTime(city, travelTime)
                .stream()
                .map(cityDtoConverter::fromEntityToDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(citiesDto, HttpStatus.OK);
    }
}
