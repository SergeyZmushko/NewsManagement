package com.mjc.school.repository.interfaces.impl;

import com.mjc.school.repository.model.impl.NewsModel;
import com.mjc.school.repository.model.impl.TagModel;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class TagRepositoryImpl implements CustomTagRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<TagModel> findByNewsModelsId(Long newsId){
        NewsModel newsModel = entityManager.find(NewsModel.class, newsId);
        if (newsModel != null){
            return newsModel.getTagModels();
        }else {
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<TagModel> findByName(String name) {
        TypedQuery<TagModel> typedQuery = entityManager
                .createQuery("SELECT a FROM TagModel a WHERE a.name = :name", TagModel.class)
                .setParameter("name", name);
        try {
            return Optional.of(typedQuery.getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }
}
