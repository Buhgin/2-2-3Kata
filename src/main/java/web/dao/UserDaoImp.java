package web.dao;

import web.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        return entityManager.createQuery("SELECT u FROM User u",User.class).getResultList();
    }

    @Override
    public void delete(User user) {
        User user1 = entityManager.merge(user);
        entityManager.remove(user1);

    }

    @Override
    public void update(User user) {
        User user1 = entityManager.find(User.class, user.getId());
        user1.setEmail(user.getEmail());
        user1.setLastName(user.getLastName());
        user1.setFirstName(user.getFirstName());
        entityManager.merge(user1);



    }
    @Override
    public User getById(Long id) {
        return entityManager.find(User.class, id);
    }


}


