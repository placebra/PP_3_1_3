package com.placebra.edu.PP_3_1_2.dao;

import com.placebra.edu.PP_3_1_2.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public User findUserByUsername(String username) {
        try {
            return em.createQuery(
                            "SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = em.createQuery("SELECT u FROM User u LEFT JOIN FETCH u.roles", User.class).getResultList();
        return users;
    }

    @Override
    public void saveUser(User user) {
        em.persist(user);
    }

    @Override
    public void removeUserById(int id) {
        User user = em.find(User.class, id);
        user.clearRoles();
        em.remove(user);
    }

    @Override
    public User getUserById(int id) {
        try {
            return em.createQuery(
                            "SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.id = :id", User.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void updateUserName(int id, String name) {
        User user = em.find(User.class, id);
        user.setName(name);
    }

    @Override
    public void updateUserEmail(int id, String email) {
        User user = em.find(User.class, id);
        user.setEmail(email);
    }

    @Override
    public void updateUserPhoneNumber(int id, String phoneNumber) {
        User user = em.find(User.class, id);
        user.setPhoneNumber(phoneNumber);
    }
}
