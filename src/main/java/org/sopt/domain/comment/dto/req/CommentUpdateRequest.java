package org.sopt.domain.comment.dto.req;

import jakarta.validation.constraints.NotBlank;

public record CommentUpdateRequest(
        @NotBlank String content
) {}
