package com.mjc.school.interfaces;

import com.mjc.school.interfaces.impl.CustomCommentRepository;
import com.mjc.school.model.impl.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>, CustomCommentRepository, JpaSpecificationExecutor<Comment> {
}
