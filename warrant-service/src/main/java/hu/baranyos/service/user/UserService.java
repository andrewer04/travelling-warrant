package hu.baranyos.service.user;

import java.util.List;

import hu.baranyos.model.entity.User;

public interface UserService {
    public void saveUser(User userDAO);

    public List<User> getAllUser();
}
