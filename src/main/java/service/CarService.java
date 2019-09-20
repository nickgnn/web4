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

    public long addCar(Car car) {
        return new CarDao(sessionFactory.openSession()).insertCar(car.getBrand(), car.getLicensePlate(), car.getModel(), car.getPrice());
    }

    public List<Car> getAll() {
        return new CarDao(sessionFactory.openSession()).getAllCars();
    }

    public Car getByName(String brand, String model, String licensePlate) {
        return new CarDao(sessionFactory.openSession()).readByName(brand, model, licensePlate);
    }

    public void delete(Car car) {
        new CarDao(sessionFactory.openSession()).deleteCar(car);
    }

    public void deleteAll() {
        new CarDao(sessionFactory.openSession()).deleteAllCars();
    }
}
