package service;

import DAO.DailyReportDao;
import model.DailyReport;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.util.List;

public class DailyReportService {
    private DailyReport dailyReport = new DailyReport();

    private static DailyReportService dailyReportService;

    private SessionFactory sessionFactory;

    private DailyReportService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static DailyReportService getInstance() {
        if (dailyReportService == null) {
            dailyReportService = new DailyReportService(DBHelper.getSessionFactory());
        }
        return dailyReportService;
    }

    public List<DailyReport> getAllDailyReports() {
        return new DailyReportDao(sessionFactory.openSession()).getAllDailyReport();
    }


    public DailyReport getLastReport() {
        List<DailyReport> list = getAllDailyReports();

        if (list.size() == 0) {
            return null;
        }

        return list.get(list.size()-1);
    }


    public long addDailyReport (DailyReport dailyReport) {
        if (dailyReport == null) {
            return new DailyReportDao(sessionFactory.openSession()).insertReport(0l, 0l);
        }

        return new DailyReportDao(sessionFactory.openSession()).insertReport(dailyReport.getEarnings(), dailyReport.getSoldCars());
    }

    public DailyReport getDailyReport() {
        return dailyReport;
    }

    public void setDailyReport(DailyReport dailyReport) {
        this.dailyReport = dailyReport;
    }

    public void delete(DailyReport dailyReport) {
        new DailyReportDao(sessionFactory.openSession()).deleteReport(dailyReport);
    }

    public void deleteAll() {
        new DailyReportDao(sessionFactory.openSession()).deleteAllReports();
    }
}