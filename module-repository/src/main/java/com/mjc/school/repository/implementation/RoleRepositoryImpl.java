package com.mjc.school.repository.implementation;

import com.mjc.school.repository.model.impl.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Repository
public class RoleRepositoryImpl extends AbstractDbRepository<Role, Long> {
    @Override
    void update(Role prevState, Role nextState) {
        prevState.setName(nextState.getName());
    }

    public Optional<Role> findByName(String name) {
        TypedQuery<Role> typedQuery = entityManager
                .createQuery("SELECT a FROM Role a WHERE a.name = :name", Role.class)
                .setParameter("name", name);
        System.out.println("find role");
        try {
            return Optional.of(typedQuery.getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }
}
