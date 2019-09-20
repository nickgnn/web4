package DAO;

import model.Car;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class CarDao {

    private Session session;

    public CarDao(Session session) {
        this.session = session;
    }

    public List<Car> getAllCars() {
        Transaction transaction = session.beginTransaction();
        List<Car> cars = session.createQuery("FROM Car").list();
        transaction.commit();
        session.close();

        return cars;
    }

    public long insertCar (String brand, String licensePlate, String model, Long price) {
        Transaction transaction = session.beginTransaction();
        long id = (Long) session.save(new Car(brand, model, licensePlate, price));
        transaction.commit();
        session.close();

        return id;
    }

    public Car readByName (String brand, String model, String licensePlate) {
        return (Car) session.createCriteria(Car.class)
                .add(Restrictions.eq("brand",brand))
                .add(Restrictions.eq("model", model))
                .add(Restrictions.eq("licensePlate", licensePlate))
                .uniqueResult();
    }

    public void deleteCar (Car car) {
        Transaction transaction = session.beginTransaction();
        session.delete(car);
        transaction.commit();
        session.close();
    }

    public void deleteAllCars() {
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM Car").executeUpdate();

        transaction.commit();
        session.close();
    }
}
