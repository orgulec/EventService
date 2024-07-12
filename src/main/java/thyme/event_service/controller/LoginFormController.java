package thyme.event_service.controller;

import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import thyme.event_service.dto.LoginDto;

@Controller
@RequestMapping//("/login")
public class LoginFormController {

    private final AuthenticationManager authenticationManager;

    public LoginFormController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/login")
    public String viewLogin(final ModelMap modelMap){
        modelMap.addAttribute("loginDto", new LoginDto());
        return "loginForm";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginDto") LoginDto loginDto) {

        Authentication authenticationRequest = UsernamePasswordAuthenticationToken
                .unauthenticated(loginDto.getUsername(), loginDto.getPassword());
        Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);

        if(authenticationResponse.isAuthenticated()) {
            return "loginForm";
        }
        return "redirect:/notFound";
    }


}