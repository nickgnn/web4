package servlet;

import com.google.gson.Gson;
import model.DailyReport;
import service.DailyReportService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NewDayServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dailyReportJSON = /*req.getParameter("dailyReport");*/DailyReportService.getInstance().getJson();


        Gson gson = new Gson();
        DailyReport dailyReport = gson.fromJson(dailyReportJSON, DailyReport.class);

        DailyReportService.getInstance().addDailyReport(dailyReport);

        System.out.println(dailyReport.getEarnings());
        System.out.println(dailyReport.getSoldCars());

        DailyReportService.getInstance().getDailyReport().setSoldCars(0L);
        DailyReportService.getInstance().getDailyReport().setEarnings(0L);

        DailyReportService.getInstance().setJson(gson.toJson(DailyReportService.getInstance().getDailyReport()));
    }
}
