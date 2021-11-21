package cinema.app.service;

import cinema.app.model.User;

public interface AuthenticationService {
    User register(String email, String password);
}
