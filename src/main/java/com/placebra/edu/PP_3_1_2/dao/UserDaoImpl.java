package com.placebra.edu.PP_3_1_2.dao;

import com.placebra.edu.PP_3_1_2.entity.Role;
import com.placebra.edu.PP_3_1_2.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    private RoleDao roleDao;

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public User findUserByEmail(String email) {
        Query query = em.createQuery("select u from User u left join fetch u.roles where u.email = :email");
        query.setParameter("email", email);
        List<User> user = query.getResultList();

        if (!user.isEmpty()) {
            return user.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<User> findAllUsers() {
        List<User> users = em.createQuery("select u from User u left join fetch u.roles", User.class).getResultList();
        return users;
    }

    @Override
    public void removeUserById(int id) {
        Query query = em.createQuery("select u from User u left join fetch u.roles where u.id = :id", User.class);
        query.setParameter("id", id);
        User user = (User) query.getSingleResult();
        user.clearRoles();
        em.remove(user);
    }

    @Override
    public void saveUser(User user) {
        em.persist(user);
    }

    @Override
    public void updateUserInfo(int id, String firstName, String lastName, int age, String email, List <String> roles, String password) {

        User user = em.createQuery("select u from User u LEFT JOIN FETCH u.roles where u.id = :id", User.class).setParameter("id", id).getSingleResult();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAge(age);
        user.setEmail(email);

        if (!password.isEmpty()) {
            user.setPassword(password);
        }

        List<String> currentRoles = user.getRoles().stream().map(Role::getName).sorted().toList();
        roles = roles.stream().sorted().toList();

        List<Role> newRoles = new ArrayList<Role>();
        if (!currentRoles.equals(roles)) {
            if (roles.contains("ROLE_ADMIN") && roles.contains("ROLE_USER")) {
                newRoles = roleDao.getAllRoles();
            } else if (roles.contains("ROLE_ADMIN")) {
                newRoles.add(roleDao.getAdminRole());
            } else if (roles.contains("ROLE_USER")) {
                newRoles.add(roleDao.getUserRole());
            }
            user.setRoles(newRoles);
        }
    }
}
