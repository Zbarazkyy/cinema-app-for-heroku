package cinema.app.controller;

import cinema.app.exception.DataProcessingException;
import cinema.app.model.RoleName;
import cinema.app.model.User;
import cinema.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        String welcomeMessage;
        if (user.getRoles().contains(RoleName.ADMIN)) {
            welcomeMessage = "Hello " + user.getEmail()
                    + System.lineSeparator()
                    + "You are added with ADMIN role. "
                    + System.lineSeparator()
                    + "GET - /cinema-halls, /movies, /movie-sessions/available, /movie-sessions/id, /users/by-email?email "
                    + System.lineSeparator()
                    + "POST - /register, /login, /cinema-halls, /movies, /movie-sessions"
                    + System.lineSeparator()
                    + "PUT - /movie-sessions/id and DELETE - /movie-sessions/id";

        } else {
            welcomeMessage = "Hello " + user.getEmail()
                    + System.lineSeparator()
                    + "You are added with USER role. "
                    + System.lineSeparator()
                    + "GET - /cinema-halls, /movies, /movie-sessions/available, /movie-sessions/id, /orders, /shopping-carts/by-user "
                    + System.lineSeparator()
                    + "POST - /register, /login, /orders/complete"
                    + System.lineSeparator()
                    + "PUT - /shopping-carts/movie-sessions?movieSessionId";
        }
        return "welcome";
    }
}
