package servlet;

import com.google.gson.Gson;
import model.Car;
import model.DailyReport;
import service.CarService;
import service.DailyReportService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        String json = gson.toJson(CarService.getInstance().getAll());

        resp.getWriter().write(json);

        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DailyReport dailyReport = DailyReportService.getInstance().getDailyReport();
        String brand = req.getParameter("brand");
        String model = req.getParameter("model");
        String licensePlate = req.getParameter("licensePlate");

        Car boughtCarByName = CarService.getInstance().getByName(brand, model, licensePlate);

        System.out.println("\n\n" + boughtCarByName.getId() + '\n' + boughtCarByName.getModel() + '\n' + boughtCarByName.getBrand() + '\n' + boughtCarByName.getPrice() + '\n');

        CarService.getInstance().delete(boughtCarByName);

        System.out.println(dailyReport.getEarnings());
        System.out.println(dailyReport.getSoldCars());

        if (dailyReport.getEarnings() == null) {
            dailyReport.setEarnings(boughtCarByName.getPrice());
        } else {
            dailyReport.setEarnings(dailyReport.getEarnings() + boughtCarByName.getPrice());
        }

        if (dailyReport.getSoldCars() == null) {
            dailyReport.setSoldCars(1L);
        } else {
            dailyReport.setSoldCars(dailyReport.getSoldCars() + 1);
        }

        System.out.println("Earnings = " + dailyReport.getEarnings());
        System.out.println("Sold cars = " + dailyReport.getSoldCars());

        Gson gson = new Gson();
        String json = gson.toJson(dailyReport);

        resp.getWriter().write(json);
        System.out.println(json);

        DailyReportService.getInstance().setJson(json);
    }
}
