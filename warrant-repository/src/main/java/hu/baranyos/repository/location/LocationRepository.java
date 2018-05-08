package hu.baranyos.repository.location;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.baranyos.model.entity.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    public Location findByName(String name);
}
