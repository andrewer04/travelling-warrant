package hu.baranyos.launcher;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import hu.baranyos.model.entity.Role;
import hu.baranyos.model.entity.User;
import hu.baranyos.repository.role.RoleRepository;
import hu.baranyos.repository.user.UserRepository;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        // create initial roles
        final Role adminRole = createRoleIfNotFound("ROLE_ADMIN");
        createRoleIfNotFound("ROLE_USER");

        // create initial user (admin)
        createUserIfNotFound("admin", "admin", new HashSet<Role>(Arrays.asList(adminRole)));

        alreadySetup = true;

    }

    @Transactional
    private User createUserIfNotFound(
            final String username,
            final String password,
            final Collection<Role> roles) {

        User user = userRepository.findByUsername(username);
        if (user == null) {
            user = new User();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            user.setEnabled(true);
        }
        user.setRoles(roles);
        user = userRepository.save(user);

        return user;

    }

    @Transactional
    private Role createRoleIfNotFound(final String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role = roleRepository.save(role);
        }
        return role;
    }

}
