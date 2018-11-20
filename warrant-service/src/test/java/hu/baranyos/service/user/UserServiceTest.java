package hu.baranyos.service.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import hu.baranyos.model.entity.User;
import hu.baranyos.repository.role.RoleRepository;
import hu.baranyos.repository.user.UserRepository;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    @TestConfiguration
    static class UserServiceImplTestContextConfiguration {

        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }
    }

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    List<User> userList = new ArrayList<>();
    List<User> foundList;
    User user;

    @Before
    public void setUp() {
        user = new User();
        user.setId(1);
        userList.add(user);

        user = new User();
        user.setId(2);
        userList.add(user);

        Mockito.when(userRepository.findAll()).thenReturn(userList);
        Mockito.when(userRepository.findById(ArgumentMatchers.anyInt()))
                .thenReturn(Optional.of(user));
    }

    @Test
    public void getAllUserTest() {
        foundList = userService.getAllUser();
        Assertions.assertThat(foundList).isEqualTo(userList);
    }

    @Test
    public void getUserTest() {
        final User foundUser = userService.getUser(2);
        Assertions.assertThat(foundUser).isEqualTo(user);
    }
}
