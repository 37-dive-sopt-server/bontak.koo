package org.sopt.domain.comment.service;

import org.sopt.domain.comment.dto.req.CommentCreateRequest;
import org.sopt.domain.comment.dto.req.CommentUpdateRequest;
import org.sopt.domain.comment.dto.res.CommentResponse;

import java.util.List;

public interface CommentService {
    void create(Long memberId, CommentCreateRequest request);

    List<CommentResponse> findByArticle(Long articleId);

    void update(Long memberId, Long commentId, CommentUpdateRequest request);

    void delete(Long memberId, Long commentId);
}
