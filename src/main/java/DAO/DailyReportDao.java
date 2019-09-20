package DAO;

import model.DailyReport;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DailyReportDao {

    private Session session;

    public DailyReportDao(Session session) {
        this.session = session;
    }

    public List<DailyReport> getAllDailyReport() {
        Transaction transaction = session.beginTransaction();
        List<DailyReport> dailyReports = session.createQuery("FROM DailyReport").list();
        transaction.commit();
        session.close();

        return dailyReports;
    }

    public long insertReport(Long earnings, Long soldCars) {
        Transaction transaction = session.beginTransaction();
        long id = (Long) session.save(new DailyReport(earnings, soldCars));
        transaction.commit();
        session.close();

        return id;
    }

    public void deleteReport (DailyReport dailyReport) {
        Transaction transaction = session.beginTransaction();
        session.delete(dailyReport);
        transaction.commit();
        session.close();
    }

    public void deleteAllReports() {
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM DailyReport").executeUpdate();

        transaction.commit();
        session.close();
    }
}
