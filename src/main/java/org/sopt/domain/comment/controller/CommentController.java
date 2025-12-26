package org.sopt.domain.comment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.sopt.domain.comment.service.CommentServiceImpl;
import org.sopt.global.api.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Article", description = "아티클 관련 API")
@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class CommentController {
    private final CommentServiceImpl articleService;

    @Operation(summary = "아티클 생성",
            description = "아티클을 생성합니다.")
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createArticle(@RequestHeader Long userId, @RequestBody CreateArticleReq req) {
        articleService.createArticle(userId, req);

        return ResponseEntity.ok(ApiResponse.created(null));
    }
}
