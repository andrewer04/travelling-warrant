package hu.baranyos.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import hu.baranyos.model.entity.User;
import hu.baranyos.repository.user.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void saveUser(final User userDAO) {
        final User user = new User();
        user.setFirstName(userDAO.getFirstName());
        user.setLastName(userDAO.getLastName());
        user.setAge(userDAO.getAge());
        user.setGender(userDAO.getGender());
        user.setPassword(passwordEncoder.encode(userDAO.getPassword()));
        user.setUsername(userDAO.getUsername());

        userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        final User user = userRepository.findByUsername(username);

        return new CustomUserDetails(user.getUsername(), user
                .getPassword(), true, true, true, true, user.getAuthorities());
    }

}
