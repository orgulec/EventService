package thyme.event_service.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import thyme.event_service.dto.NewEventDto;
import thyme.event_service.event.EventModel;
import thyme.event_service.event.EventService;

@Controller
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    @GetMapping
    public String choose(final ModelMap modelMap){
        modelMap.addAttribute("result", "Just peek what do you need...");
//        return "redirect:/event";
        return "events";
    }
    @GetMapping("/hello")
    public String sayHello(final ModelMap modelMap){
        String welcomeText = "Welcome in EventService - simple web application to manage events.<br>Here You can create new events, search for interesting event, write comments, add ratings and more!";
        modelMap.addAttribute("result", welcomeText);
        return "events";
    }
    @GetMapping("/bye")
    public String sayBye(final ModelMap modelMap){
        String goodbyeText = "Goodbye and see you soon!";
        modelMap.addAttribute("result", goodbyeText);
        return "events";
    }
    @GetMapping("/all")
    public String getById(ModelMap modelMap){
        modelMap.addAttribute("result", eventService.getAll());
        return "events_all";
    }
    @GetMapping("/{id}")
    public String getById(ModelMap modelMap, @PathVariable long id){
        modelMap.addAttribute("result", eventService.getById(id));
        return "event";
    }
    @GetMapping("/search")
    public String getById(ModelMap modelMap, @RequestParam("toFind") String toFind){
        modelMap.addAttribute("result", eventService.getByText(toFind));
        return "events_all";
    }
    @PostMapping("/{id}/signing")
    public String signIn(ModelMap modelMap, @PathVariable long id){
        modelMap.addAttribute("result", eventService.signMeIn(id));
        return "redirect:/events"+'/'+id;
    }
    @GetMapping("/new")
    public String newEvent(@ModelAttribute("newEvent") NewEventDto newEvent){
        return "event_new";
    }
    @PostMapping("/add")
    public String addEvent(@Valid @ModelAttribute("newEvent") NewEventDto newEvent, ModelMap modelMap){
        modelMap.addAttribute("result", newEvent);
        EventModel event = eventService.addEvent(newEvent);
        return "redirect:/events"+'/'+event.getId();
    }


}
