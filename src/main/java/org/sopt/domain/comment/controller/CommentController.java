package org.sopt.domain.comment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sopt.domain.comment.dto.req.CommentCreateRequest;
import org.sopt.domain.comment.dto.req.CommentUpdateRequest;
import org.sopt.domain.comment.dto.res.CommentResponse;
import org.sopt.domain.comment.service.CommentServiceImpl;
import org.sopt.domain.member.repository.JwtService;
import org.sopt.global.api.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Comment", description = "댓글 관련 API")
@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentServiceImpl commentService;
    private final JwtService jwtService;

    @Operation(summary = "댓글 생성")
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> create(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
            @Valid @RequestBody CommentCreateRequest request
    ) {
        Long memberId = extractMemberId(authorization);
        commentService.create(memberId, request);
        return ResponseEntity.ok(ApiResponse.ok(null));
    }

    @Operation(summary = "댓글 목록 조회")
    @GetMapping
    public ResponseEntity<ApiResponse<List<CommentResponse>>> list(
            @RequestParam Long articleId
    ) {
        return ResponseEntity.ok(
                ApiResponse.ok(commentService.findByArticle(articleId))
        );
    }

    @Operation(summary = "댓글 수정")
    @PatchMapping("/{commentId}")
    public ResponseEntity<ApiResponse<Void>> update(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
            @PathVariable Long commentId,
            @Valid @RequestBody CommentUpdateRequest request
    ) {
        Long memberId = extractMemberId(authorization);
        commentService.update(memberId, commentId, request);
        return ResponseEntity.ok(ApiResponse.ok(null));
    }

    @Operation(summary = "댓글 삭제")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
            @PathVariable Long commentId
    ) {
        Long memberId = extractMemberId(authorization);
        commentService.delete(memberId, commentId);
        return ResponseEntity.ok(ApiResponse.ok(null));
    }

    private Long extractMemberId(String authorization) {
        String token = authorization.replace("Bearer ", "").trim();
        return jwtService.verifyAndGetMemberId(token);
    }
}
