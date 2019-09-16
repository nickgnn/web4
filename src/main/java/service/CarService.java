package service;

import DAO.CarDao;
import model.Car;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.util.List;

public class CarService {

    private static CarService carService;

    private SessionFactory sessionFactory;

    private CarService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static CarService getInstance() {
        if (carService == null) {
            carService = new CarService(DBHelper.getSessionFactory());
        }
        return carService;
    }

    public long addCar(String brand, String model, String licensePlate, Long price) {
        return new CarDao(sessionFactory.openSession()).insertCar(brand, licensePlate, model, price);
    }

    public List<Car> getAllCars() {
        return new CarDao(sessionFactory.openSession()).getAllCars();
    }

//    public Car getByName(String name) {
//        return new CarDao(sessionFactory.openSession())
//    }
}
