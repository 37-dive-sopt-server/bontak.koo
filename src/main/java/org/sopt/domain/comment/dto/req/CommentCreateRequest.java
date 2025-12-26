package org.sopt.domain.comment.dto.req;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CommentCreateRequest(
        @NotNull Long articleId,
        @NotBlank String content
) {}
