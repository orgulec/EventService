package thyme.event_service.controller;

import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import thyme.event_service.dto.LoginDto;

import java.security.Principal;

@Controller
@RequestMapping
public class LoginController {

    private final AuthenticationManager authenticationManager;

    public LoginController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/login")
    public String viewLogin(final ModelMap modelMap){
        modelMap.addAttribute("loginDto", new LoginDto());
        return "/login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginDto") LoginDto loginDto, Errors errors, ModelMap modelMap) {
        if(errors.hasErrors()){
            return "redirect:login/error";
        }
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken
                .unauthenticated(loginDto.getUsername(), loginDto.getPassword());
        Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);

        modelMap.addAttribute("authentication", authenticationResponse.isAuthenticated());
        return "redirect:login/in";
    }
    @GetMapping("/in")
    public String getIn(@ModelAttribute("authentication") Boolean authentication, ModelMap modelMap){
        modelMap.addAttribute("loginResult","Successfully logged in!");
        return "login";
    }
    @GetMapping("/error")
    public String getErrors(@ModelAttribute("authentication") Boolean authentication, ModelMap modelMap){
        modelMap.addAttribute("loginResult", "Wrong username or password!");
        return "login";
    }

}