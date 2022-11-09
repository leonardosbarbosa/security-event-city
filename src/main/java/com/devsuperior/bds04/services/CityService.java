package com.devsuperior.bds04.services;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

    @Autowired
    private CityRepository repository;

   @Transactional(readOnly = true)
    public List<CityDTO> findAllCities() {
        return repository.findAll(Sort.by("name")).stream().map(CityDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public CityDTO saveNewCity(CityDTO cityDTO) {
        City city = new City();
        city.setName(cityDTO.getName());
        city = repository.save(city);
        return new CityDTO(city);
    }
}
