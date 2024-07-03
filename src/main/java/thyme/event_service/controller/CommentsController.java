package thyme.event_service.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import thyme.event_service.comments.CommentsModel;
import thyme.event_service.comments.CommentsService;
import thyme.event_service.dto.NewCommentDto;
import thyme.event_service.event.EventService;

@Controller
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentsController {

    private final CommentsService commentsService;
    private final EventService eventService;

    @GetMapping("/read/{eventId}")
    public String readComments(@PathVariable Long eventId, final ModelMap modelMap){
        modelMap.addAttribute("readComments", commentsService.findByEventId(eventId));
        return "comments";
    }

    @GetMapping("/new/{eventId}")
    public String newComment(@PathVariable Long eventId, ModelMap modelMap){
        modelMap.addAttribute("eventId", eventId);
        return "redirect:/comments/add/{eventId}";
//        return "comments";
    }
    @PostMapping("/add/{eventId}")
    public String addComment(@Valid @ModelAttribute("newComment") NewCommentDto newComment, @PathVariable Long eventId, ModelMap modelMap){
        newComment.setEvent(eventService.getById(eventId));
        modelMap.addAttribute("newComment", newComment);
        commentsService.addComment(newComment);
        return "redirect:/comments/read/{eventId}";
    }


}
