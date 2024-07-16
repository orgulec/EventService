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
class LoginController {

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
            return "redirect:login/in";
        }
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken
                .unauthenticated(loginDto.getUsername(), loginDto.getPassword());
        Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);

        modelMap.addAttribute("authentication", authenticationResponse);
        return "redirect:login/in";
    }
    @GetMapping("/in")
    public String getIn(@ModelAttribute("authentication") Authentication authentication, ModelMap modelMap){
        if(authentication.isAuthenticated()){
            modelMap.addAttribute("loginResult","Successfully logged in!");
        } else{
            modelMap.addAttribute("loginResult", "Wrong username or password!");
        }
        return "login";
    }

}