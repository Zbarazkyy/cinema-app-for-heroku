package cinema.app.controller;

import cinema.app.exception.DataProcessingException;
import cinema.app.model.User;
import cinema.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomePageController {
    private UserService userService;

    @Autowired
    public WelcomePageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String welcomePage(Authentication auth, Model model) {
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String email = userDetails.getUsername();
        User user = userService.findByEmail(email).orElseThrow(
                () -> new DataProcessingException("User with email " + email + " not found"));
        model.addAttribute("userName", user.getEmail());
        return "welcome";
    }
}
