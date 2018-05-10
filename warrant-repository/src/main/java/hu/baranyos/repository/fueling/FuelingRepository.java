package hu.baranyos.repository.fueling;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.baranyos.model.entity.Fueling;

@Repository
public interface FuelingRepository extends JpaRepository<Fueling, Integer> {
    public List<Fueling> findByDateAfter(Date date);
}
