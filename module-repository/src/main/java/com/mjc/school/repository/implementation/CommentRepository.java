package com.mjc.school.repository.implementation;

import com.mjc.school.repository.model.impl.Comment;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;


@Repository
public class CommentRepository extends AbstractDbRepository<Comment, Long> {

    @Override
    void update(Comment prevState, Comment nextState) {
        prevState.setContent(nextState.getContent());
        prevState.setNewsModel(nextState.getNewsModel());
    }

    public List<Comment> readCommentsByNewsId(Long newsId) {
        TypedQuery<Comment> typedQuery = entityManager
                .createQuery("SELECT a FROM Comment a INNER JOIN a.newsModel n WHERE n.id=:newsId", Comment.class)
                .setParameter("newsId", newsId);
        try {
            return typedQuery.getResultList();
        } catch (NoResultException ex) {
            return Collections.emptyList();
        }
    }
}
