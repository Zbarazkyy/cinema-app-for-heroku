package cinema.app.config;

import cinema.app.model.Role;
import cinema.app.model.RoleName;
import cinema.app.model.User;
import cinema.app.service.AuthenticationService;
import cinema.app.service.RoleService;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private AuthenticationService authenticationService;
    private RoleService roleService;

    public DataInitializer(AuthenticationService authenticationService, RoleService roleService) {
        this.authenticationService = authenticationService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void inject() {
        Role adminRole = new Role();
        adminRole.setName(RoleName.ADMIN);
        roleService.add(adminRole);

        Role userRole = new Role();
        userRole.setName(RoleName.USER);
        roleService.add(userRole);

        User admin = new User();
        admin.setEmail("admin@gmail.com");
        admin.setPassword("12345678");
        admin.setRoles(Set.of(adminRole));
        authenticationService.register(admin.getEmail(), admin.getPassword());
        User user = new User();
        user.setEmail("user@gmail.com");
        user.setPassword("87654321");
        user.setRoles(Set.of(userRole));
        authenticationService.register(user.getEmail(), user.getPassword());
    }
}
