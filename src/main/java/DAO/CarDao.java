package DAO;

import model.Car;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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

//    public Car readByName (String name) {
//
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<Car> criteriaQuery = builder.createQuery(Car.class);
//
//        criteriaQuery.from(Car.class);
//        session.createQuery(criteriaQuery);
//
//        session.close();
//        return
//    }
}
