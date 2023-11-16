package com.mjc.school.repository.interfaces;

import com.mjc.school.repository.model.impl.Comment;
import com.mjc.school.repository.interfaces.impl.CustomCommentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>, CustomCommentRepository {
}
