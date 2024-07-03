package thyme.event_service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.proxy.EntityNotFoundDelegate;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class EventService {
    private final EventRepository eventRepository;

    public Set<EventModel> getAll(){
        Set<EventModel> events = new HashSet<>(eventRepository.findAll());
        return events;
    }

    public EventModel getById(long id){
        Optional<EventModel> event = eventRepository.findById(id);
        if(event.isEmpty()){
            throw new EntityNotFoundException("Event not found");
        }
        return event.get();
    }
}
