package hu.baranyos.repository.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.baranyos.model.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    public Role findByName(String name);
}
