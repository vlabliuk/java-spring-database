package ua.labliuk.springcourse.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.labliuk.springcourse.models.User;
import java.util.List;

@Repository
public class UserDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<User> index(){
        Session session = sessionFactory.getCurrentSession();
        List<User> users;
        users = session.createQuery("from User", User.class ).list();
        return users;
    }

    @Transactional
    public User show(int id) {
            Session session = sessionFactory.getCurrentSession();
        return session.get(User.class,id);
    }

    @Transactional
    public void save(User user){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);
    }

    @Transactional
    public void update(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class,id);
        session.remove(user);
    }
}
