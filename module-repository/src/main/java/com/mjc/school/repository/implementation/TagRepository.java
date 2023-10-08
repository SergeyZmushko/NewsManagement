package com.mjc.school.repository.implementation;

import com.mjc.school.repository.model.impl.TagModel;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class TagRepository extends AbstractDbRepository<TagModel, Long> {

    public List<TagModel> readByNewsId(Long newsId) {
        TypedQuery<TagModel> typedQuery = entityManager
                .createQuery("SELECT t FROM TagModel t INNER JOIN t.newsModels n WHERE n.id=:newsId", TagModel.class)
                .setParameter("newsId", newsId);
        return typedQuery.getResultList();
    }

    public Optional<TagModel> readByName(String name) {
        TypedQuery<TagModel> typedQuery = entityManager
                .createQuery("SELECT t FROM TagModel t WHERE t.name=:name", TagModel.class)
                .setParameter("name", name);
        try{
            return Optional.of(typedQuery.getSingleResult());
        }catch (NoResultException ex){
            return Optional.empty();
        }
    }

    @Override
    void update(TagModel prevState, TagModel nextState) {
        if (nextState.getName() != null && !nextState.getName().isBlank()){
            prevState.setName(nextState.getName());
        }
        prevState.setName(nextState.getName());
    }
}
