package com.mjc.school.repository.interfaces.impl;

import com.mjc.school.repository.model.impl.Role;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

public class RoleRepositoryImpl implements CustomRoleRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Role> findByName(String name) {
        TypedQuery<Role> typedQuery = entityManager
                .createQuery("SELECT a FROM Role a WHERE a.name = :name", Role.class)
                .setParameter("name", name);
        try {
            return Optional.of(typedQuery.getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }
}
