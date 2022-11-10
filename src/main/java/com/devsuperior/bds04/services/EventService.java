package com.devsuperior.bds04.services;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {

    @Autowired
    EventRepository repository;

    @Transactional(readOnly = true)
    public Page<EventDTO> findAll(Pageable pageable) {
        Page<Event> events = repository.findAll(pageable);
        return events.map(EventDTO::new);
    }

    @Transactional
    public EventDTO insert(EventDTO eventDTO) {
        Event event = new Event();
        event.setName(eventDTO.getName());
        event.setUrl(eventDTO.getUrl());
        event.setDate(eventDTO.getDate());
        City city = new City();
        city.setId(eventDTO.getCityId());
        event.setCity(city);
        event = repository.save(event);
        return new EventDTO(event);
    }
}
