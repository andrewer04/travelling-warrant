package hu.baranyos.service.report;

import java.util.List;

import hu.baranyos.model.entity.Report;
import hu.baranyos.model.entity.Vehicle;

public interface ReportService {
    public List<Report> getAllReport();

    public void saveReport(Vehicle vehicle);

    public Report getReport(Integer id);
}
