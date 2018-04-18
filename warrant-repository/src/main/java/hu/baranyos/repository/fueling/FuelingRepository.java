package hu.baranyos.repository.fueling;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.baranyos.model.entity.Fueling;

public interface FuelingRepository extends JpaRepository<Fueling, Integer> {

}
