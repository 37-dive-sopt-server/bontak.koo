package org.sopt.domain.comment.dto.res;

import java.time.LocalDateTime;

public record CommentResponse(
        Long commentId,
        Long memberId,
        String memberName,
        String content,
        LocalDateTime createdAt
) {}
