package hu.baranyos.service.report;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.baranyos.model.entity.Report;
import hu.baranyos.repository.report.ReportRepository;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportRepository reportRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Report> getAllReport() {
        return reportRepository.findAll();
    }

    @Override
    @Transactional
    public void saveReport() {
        final Report report = new Report();
        report.setDate(new Date());

        reportRepository.save(report);
    }

    @Override
    @Transactional(readOnly = true)
    public Report getReport(final Integer id) {
        return reportRepository.findOne(id);
    }

}
