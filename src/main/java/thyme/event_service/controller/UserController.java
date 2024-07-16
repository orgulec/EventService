package thyme.event_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import thyme.event_service.user.UserModel;
import thyme.event_service.user.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
class UserController {

        private final UserService userService;

        @GetMapping("/{id}")
        public String show(final ModelMap modelMap, @PathVariable long id){
            UserModel user = userService.getById(id);
            modelMap.addAttribute("result", user);
            return "user";
        }
        @GetMapping("/me")
        public String show(final ModelMap modelMap, Principal principal){
            UserModel user = userService.getByUsername(principal.getName());
            modelMap.addAttribute("result", user);
            return "user";
        }

}
