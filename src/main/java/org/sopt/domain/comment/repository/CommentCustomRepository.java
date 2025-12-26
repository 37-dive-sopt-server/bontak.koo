package org.sopt.domain.comment.repository;

import org.sopt.domain.comment.entity.Comment;

import java.util.List;

public interface CommentCustomRepository {
    List<Comment> findAllWithMember();
}
