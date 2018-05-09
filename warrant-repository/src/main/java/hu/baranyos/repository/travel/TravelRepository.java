package hu.baranyos.repository.travel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.baranyos.model.entity.Travel;

@Repository
public interface TravelRepository extends JpaRepository<Travel, Integer> {}
