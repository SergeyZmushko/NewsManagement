package com.mjc.school.repository.implementation;

import com.mjc.school.repository.model.impl.AuthorModel;
import org.springframework.stereotype.Repository;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Repository
public class AuthorRepository extends AbstractDbRepository<AuthorModel, Long> {

    @Override
    void update(AuthorModel prevState, AuthorModel nextState) {
        prevState.setName(nextState.getName());
    }

    public Optional<AuthorModel> readByNewsId(Long newsId) {
        TypedQuery<AuthorModel> typedQuery = entityManager
                .createQuery("SELECT a FROM AuthorModel a INNER JOIN a.news n WHERE n.id=:newsId", AuthorModel.class)
                .setParameter("newsId", newsId);
        try {
            return Optional.of(typedQuery.getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    public Optional<AuthorModel> readByName(String name){
        TypedQuery<AuthorModel> typedQuery = entityManager
                .createQuery("SELECT a FROM AuthorModel a WHERE a.name = :name", AuthorModel.class)
                .setParameter("name", name);
        try {
            return Optional.of(typedQuery.getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

}
