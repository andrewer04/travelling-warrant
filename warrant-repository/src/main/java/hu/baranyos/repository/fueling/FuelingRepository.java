package hu.baranyos.repository.fueling;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.baranyos.model.entity.Fueling;

@Repository
public interface FuelingRepository extends JpaRepository<Fueling, Integer> {

}
