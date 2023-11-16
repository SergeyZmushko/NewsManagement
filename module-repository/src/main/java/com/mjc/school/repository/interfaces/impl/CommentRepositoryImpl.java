package com.mjc.school.repository.interfaces.impl;

import com.mjc.school.repository.model.impl.Comment;
import com.mjc.school.repository.model.impl.NewsModel;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

public class CommentRepositoryImpl implements CustomCommentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Comment> findByNewsModelId(Long newsId) {
        NewsModel newsModel = entityManager.find(NewsModel.class, newsId);
        if (newsId != null){
            return newsModel.getComments();
        }
        return Collections.emptyList();
    }
}
