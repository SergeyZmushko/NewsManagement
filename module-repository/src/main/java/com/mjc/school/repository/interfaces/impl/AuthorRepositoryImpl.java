package com.mjc.school.repository.interfaces.impl;

import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.repository.model.impl.NewsModel;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

public class AuthorRepositoryImpl implements CustomAuthorRepository{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Optional<AuthorModel> findByName(String name) {
        TypedQuery<AuthorModel> typedQuery = entityManager
                .createQuery("SELECT a FROM AuthorModel a WHERE a.name = :name", AuthorModel.class)
                .setParameter("name", name);
        try {
            return Optional.of(typedQuery.getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<AuthorModel> readByNewsId(Long newsId) {
        NewsModel newsModel = entityManager.find(NewsModel.class, newsId);
        return Optional.of(newsModel.getAuthorModel());
    }
}
