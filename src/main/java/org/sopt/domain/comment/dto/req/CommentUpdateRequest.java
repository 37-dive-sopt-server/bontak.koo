package org.sopt.domain.comment.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record CommentUpdateRequest(

        @NotBlank
        @Size(max = 300, message = "댓글 내용은 최대 300자까지 입력할 수 있습니다.")
        String content
) {}
