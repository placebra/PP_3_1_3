package com.placebra.edu.PP_3_1_2.dao;

import com.placebra.edu.PP_3_1_2.entity.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Role getUserRole() {
        TypedQuery<Role> query = em.createQuery("SELECT r FROM Role r WHERE r.name = :name", Role.class);
        query.setParameter("name", "ROLE_USER");
        return query.getSingleResult();
    }

    @Override
    public Role getAdminRole() {
        TypedQuery<Role> query = em.createQuery("SELECT r FROM Role r WHERE r.name = :name", Role.class);
        query.setParameter("name", "ROLE_ADMIN");
        return query.getSingleResult();
    }

    @Override
    public List<Role> getAllRoles() {
        Query query = em.createQuery("SELECT r FROM Role r", Role.class);
        return query.getResultList();
    }
}
