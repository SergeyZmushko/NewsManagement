package com.mjc.school.repository.interfaces.impl;

import com.mjc.school.repository.model.impl.Comment;

import java.util.List;

public interface CustomCommentRepository {
    List<Comment> findByNewsModelId(Long newsId);
}
