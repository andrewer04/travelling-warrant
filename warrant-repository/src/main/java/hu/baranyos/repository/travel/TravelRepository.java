package hu.baranyos.repository.travel;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.baranyos.model.entity.Travel;

@Repository
public interface TravelRepository extends JpaRepository<Travel, Integer> {
    public List<Travel> findByDateAfter(Date date);
}
