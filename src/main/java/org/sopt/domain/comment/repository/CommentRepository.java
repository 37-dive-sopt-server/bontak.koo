package org.sopt.domain.comment.repository;

import org.sopt.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByArticleIdAndIsDeletedFalseOrderByCreatedAtAsc(Long articleId);
}
