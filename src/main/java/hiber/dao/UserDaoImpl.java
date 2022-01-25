package hiber.dao;

import hiber.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{
    @Autowired
    private SessionFactory sessionFactory;


    public  UserDaoImpl(){}

    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }
    // Метод выбора вытаскивания модели
    @Override
    public User getUserByModelSeries(String model, int series) {
        String query = "SELECT user FROM User user WHERE user.car.model = :model AND user.car.series = :series ";
        try{
            TypedQuery<User> typedQuery = sessionFactory.openSession().createQuery(query);
            typedQuery.setParameter("model", model);
            typedQuery.setParameter("series", series);
            return typedQuery.getSingleResult();
        } catch (HibernateException | NonUniqueResultException e) {
            e.printStackTrace();
        }
        return null;
    }
}
