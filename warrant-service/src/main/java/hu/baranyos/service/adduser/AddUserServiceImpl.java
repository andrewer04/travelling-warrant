package hu.baranyos.service.adduser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.baranyos.model.entity.User;
import hu.baranyos.repository.user.UserRepository;

@Service
public class AddUserServiceImpl implements AddUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveUser(final User userDAO) {
        final User user = new User();
        user.setFirstName(userDAO.getFirstName());
        user.setLastName(userDAO.getLastName());
        user.setAge(userDAO.getAge());
        user.setGender(userDAO.getGender());
        user.setPassword(userDAO.getPassword());

        userRepository.save(user);
    }

}
