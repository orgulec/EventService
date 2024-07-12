package thyme.event_service.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import thyme.event_service.dto.NewEventDto;
import thyme.event_service.event.EventModel;
import thyme.event_service.event.EventService;
import thyme.event_service.exceptions.WrongInputDtoException;

import java.util.Arrays;

@Controller
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    @GetMapping
    public String choose(final ModelMap modelMap){
        modelMap.addAttribute("result", "Just peek what do you need...");
        return "events";
    }
    @GetMapping("/hello")
    public String sayHello(final ModelMap modelMap){
        String welcomeText = "Welcome in EventService - simple web application to manage events." +
                "Here You can create new events, search for interesting event, write comments, add ratings and more!";
        modelMap.addAttribute("result", welcomeText);
        return "events";
    }
    @GetMapping("/bye")
    public String sayBye(final ModelMap modelMap){
        String goodbyeText = "Goodbye and see you soon!";
        modelMap.addAttribute("result", goodbyeText);
//        return "events";
        return "redirect:/logout";
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
    @PostMapping("/subscribe/{eventId}")
    public String signIn(ModelMap modelMap, @PathVariable long eventId, HttpSession session){
        Object attribute = session.getAttribute("sec-fetch-user");
        EventModel event = eventService.signMeIn(eventId);
        modelMap.addAttribute("result", event);
        modelMap.addAttribute("session", attribute);
        return "redirect:/events/"+eventId;
    }
    @GetMapping("/new")
    public String newEvent(final ModelMap modelMap){
        modelMap.addAttribute("newEvent", new NewEventDto());
        return "event_new";
    }

    @PostMapping("/new")
    public String addEvent(@Valid @ModelAttribute("newEvent") NewEventDto newEvent, BindingResult bindingResult, ModelMap modelMap){
        modelMap.addAttribute("result", newEvent);
        if(bindingResult.hasErrors()){
            return "event_new";
        }
        EventModel event = eventService.addEvent(newEvent);
        return "redirect:/events/"+event.getId();
    }


}
