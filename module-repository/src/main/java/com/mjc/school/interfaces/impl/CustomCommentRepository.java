package com.mjc.school.interfaces.impl;

import com.mjc.school.model.impl.Comment;

import java.util.List;

public interface CustomCommentRepository {
    List<Comment> findByNewsModelId(Long newsId);
}
