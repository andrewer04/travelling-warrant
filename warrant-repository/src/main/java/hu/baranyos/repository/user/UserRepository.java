package hu.baranyos.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.baranyos.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByUsername(String username);
}
