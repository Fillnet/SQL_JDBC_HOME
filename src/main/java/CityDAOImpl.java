import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CityDAOImpl implements CityDAO {

    @Override
    public void addCity(City city) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.save(city);
            transaction.commit();
        }
    }

    @Override
    public City getCityById(int id) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.get(City.class, id);
        }
    }

    @Override
    public List<City> getAllCities() {
        List<City> cities = (List<City>) HibernateConfig
                .getSessionFactory().openSession().createQuery("FROM City").list();
        return cities;
    }

    @Override
    public void updateCity(City city) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.update(city);
            transaction.commit();
        }
    }

    @Override
    public void deleteCity(City city) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.delete(city);
            transaction.commit();
        }
    }
}
