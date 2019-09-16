package servlet;

import com.google.gson.Gson;
import model.Car;
import service.CarService;
import service.DailyReportService;
import util.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> fields = new LinkedHashMap<>();
        fields.put("List", "");
        Gson gson = new Gson();
        List<Car> cars = CarService.getInstance().getAllCars();
        String json = gson.toJson(cars);

        for (int i = 0; i < cars.size(); i++) {
            fields.put("car_" + cars.get(i).getId(), cars.get(i).getBrand() + " " + cars.get(i).getLicensePlate() + " " + cars.get(i).getModel() + " " + cars.get(i).getPrice());
        }

        resp.getWriter().println(PageGenerator.getInstance().getPage("customerPage.html", fields));

        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long price = Long.valueOf(req.getParameter("price"));

//        long earnings =
////        long soldCars =
////
////        DailyReportService.getInstance().addDailyReport();
//
//        DailyReportService.getInstance().addDailyReport()
//        CarService.getInstance().
    }
}
