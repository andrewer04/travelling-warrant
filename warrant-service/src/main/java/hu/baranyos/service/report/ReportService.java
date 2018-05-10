package hu.baranyos.service.report;

import java.util.List;

import hu.baranyos.model.entity.Report;

public interface ReportService {
    public List<Report> getAllReport();

    public void saveReport();

    public Report getReport(Integer id);
}
