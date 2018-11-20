package hu.baranyos.service.report;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import hu.baranyos.model.entity.Report;
import hu.baranyos.repository.report.ReportRepository;
import hu.baranyos.service.fueling.FuelingService;
import hu.baranyos.service.travel.TravelService;

@RunWith(SpringRunner.class)
public class ReportServiceTest {

    @TestConfiguration
    static class ReportServiceImplTestContextConfiguration {

        @Bean
        public ReportService reportService() {
            return new ReportServiceImpl();
        }
    }

    @Autowired
    private ReportService reportService;

    @MockBean
    private ReportRepository reportRepository;

    @MockBean
    private TravelService travelService;

    @MockBean
    private FuelingService fuelingService;

    List<Report> reportList = new ArrayList<>();
    List<Report> foundList;
    Report report;

    @Before
    public void setUp() {
        report = new Report();
        report.setId(1);
        reportList.add(report);

        report = new Report();
        report.setId(2);
        reportList.add(report);

        Mockito.when(reportRepository.findAll()).thenReturn(reportList);
        Mockito.when(reportRepository.findById(ArgumentMatchers.anyInt()))
                .thenReturn(Optional.of(report));
    }

    @Test
    public void getAllReportTest() {
        foundList = reportService.getAllReport();
        Assertions.assertThat(foundList).isEqualTo(reportList);
    }

    @Test
    public void getReportTest() {
        final Report foundReport = reportService.getReport(2);
        Assertions.assertThat(foundReport).isEqualTo(report);
    }

}
