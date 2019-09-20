package servlet;

import model.Car;
import service.CarService;
import util.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ProducerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> fields = new HashMap<>();
        fields.put("message", "");

        resp.getWriter().println(PageGenerator.getInstance().getPage("producerPage.html", fields));

        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String brand = req.getParameter("brand");
        String model = req.getParameter("model");
        String licensePlate = req.getParameter("licensePlate");
        Long price = Long.valueOf(req.getParameter("price"));
        Map<String, Object> fields = new HashMap<>();
        fields.put("message", "");

        Car car = new Car(brand, model, licensePlate, price);

        long id = -1;

        if ((id = CarService.getInstance().addCar(car)) != -1) {
            fields.put("message", "Car added succesful");
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            fields.put("message", "Car not added");
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }

        resp.getWriter().println(PageGenerator.getInstance().getPage("producerPage.html", fields));
    }
}
