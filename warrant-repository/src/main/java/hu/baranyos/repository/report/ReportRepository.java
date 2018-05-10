package hu.baranyos.repository.report;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.baranyos.model.entity.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {

}
