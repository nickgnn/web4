package servlet;

import com.google.gson.Gson;
import model.DailyReport;
import service.CarService;
import service.DailyReportService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DailyReportServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo().contains("all")) {
            List<DailyReport> allDailyReports = DailyReportService.getInstance().getAllDailyReports();
            Gson gson = new Gson();

            resp.getWriter().write(gson.toJson(allDailyReports));
        } else if (req.getPathInfo().contains("last")) {
            Gson gson = new Gson();
            resp.getWriter().write(gson.toJson(DailyReportService.getInstance().getLastReport()));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CarService.getInstance().deleteAll();
        DailyReportService.getInstance().deleteAll();
    }
}
