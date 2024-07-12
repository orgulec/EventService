package thyme.event_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import thyme.event_service.user.UserModel;
import thyme.event_service.user.UserService;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

        private final UserService userService;

        @GetMapping("/{id}")
        public String show(final ModelMap modelMap, @PathVariable long id){
            UserModel user = userService.getById(id);
            modelMap.addAttribute("result", user);
            return "user";
        }

//    @GetMapping(path = "/foo")
//    public void foo(HttpSession session) {
//        String sessionId = session.getId();
//        session.toString();
//    }
}
