package hu.baranyos.service.report;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.baranyos.model.entity.Fueling;
import hu.baranyos.model.entity.Report;
import hu.baranyos.model.entity.Travel;
import hu.baranyos.model.entity.User;
import hu.baranyos.model.entity.Vehicle;
import hu.baranyos.repository.report.ReportRepository;
import hu.baranyos.service.fueling.FuelingService;
import hu.baranyos.service.travel.TravelService;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    TravelService travelService;

    @Autowired
    FuelingService fuelingService;

    @Override
    @Transactional(readOnly = true)
    public List<Report> getAllReport() {
        return reportRepository.findAll();
    }

    @Override
    @Transactional
    public void saveReport(final Vehicle vehicle) {
        final Date lastDate = updateLastDate();
        final List<Travel> travelList = travelService.getByDateAfter(lastDate);
        final List<Fueling> fuelingList = fuelingService.getByDateAfter(lastDate);
        final Set<User> userList = getUsers(travelList);

        final Report report = new Report();
        report.setVehicle(vehicle);
        report.setDate(new Date());

        final int distanceSum = calculateDistanceSum(travelList);
        final int fuelingsum = calculateFuelingSum(fuelingList);
        final Map<User, Integer> userFuelings = getUserFuelings(fuelingList);
        final Map<User, Integer> userKm = getUserKm(travelList);

        report.setDistanceSum(distanceSum);
        report.setFuelingSum(fuelingsum);
        report.setUsers(userList);
        report.setUserKm(userKm);
        report.setUserFuelings(userFuelings);
        report.setUserBalance(calculateBalance(distanceSum, fuelingsum, userKm, userFuelings, userList));

        reportRepository.save(report);
    }

    @Override
    @Transactional(readOnly = true)
    public Report getReport(final Integer id) {
        return reportRepository.findOne(id);
    }

    private Date updateLastDate() {
        final Report lastReport = reportRepository.findTopByOrderByDateDesc();
        if (lastReport == null) {
            return null;
        }
        return lastReport.getDate();
    }

    private int calculateDistanceSum(final List<Travel> travelList) {
        int sum = 0;
        for (final Travel travel : travelList) {
            sum += travel.getDistance();
        }
        return sum;
    }

    private int calculateFuelingSum(final List<Fueling> fuelingList) {
        int sum = 0;
        for (final Fueling fueling : fuelingList) {
            sum += fueling.getAmount();
        }
        return sum;
    }

    private Set<User> getUsers(final List<Travel> travelList) {
        final Set<User> list = new HashSet();
        for (final Travel travel : travelList) {
            for (final User user : travel.getUsers()) {
                list.add(user);
            }
        }
        return list;
    }

    private Map<User, Integer> getUserKm(final List<Travel> travelList) {
        final Map<User, Integer> map = new HashMap<>();
        for (final Travel travel : travelList) {
            final int userCount = travel.getUsers().size();
            for (final User user : travel.getUsers()) {
                final int previousKm = map.get(user) == null ? 0 : map.get(user);
                map.put(user, previousKm + travel.getDistance() / userCount);
            }
        }
        return map;
    }

    private Map<User, Integer> getUserFuelings(final List<Fueling> fuelingList) {
        final Map<User, Integer> map = new HashMap<>();
        for (final Fueling fueling : fuelingList) {
            final User user = fueling.getUser();
            final int previousFueling = map.get(user) == null ? 0 : map.get(user);
            map.put(user, previousFueling + fueling.getAmount());
        }
        return map;
    }

    private Map<User, Integer> calculateBalance(
            final int distanceSum,
            final int fuelingSum,
            final Map<User, Integer> userkm,
            final Map<User, Integer> userFuelings,
            final Set<User> userList) {
        final Map<User, Integer> map = new HashMap<>();
        final int kmCost = fuelingSum / distanceSum;
        for (final User user : userList) {
            final int minCost = userkm.get(user) * kmCost;
            final int actual = userFuelings.get(user) == null ? 0 : userFuelings.get(user);
            final int balance = actual - minCost;
            map.put(user, balance);
        }
        return map;
    }

}
