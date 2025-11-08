package org.sopt.domain.article.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.sopt.domain.article.dto.req.CreateArticleReq;
import org.sopt.domain.article.dto.res.AllArticleRes;
import org.sopt.domain.article.dto.res.ArticleRes;
import org.sopt.domain.article.service.ArticleServiceImpl;
import org.sopt.global.api.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Article", description = "아티클 관련 API")
@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleServiceImpl articleService;

    @Operation(summary = "아티클 생성",
            description = "아티클을 생성합니다.")
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createArticle(@RequestHeader Long userId, @RequestBody CreateArticleReq req) {
        articleService.createArticle(userId, req);

        return ResponseEntity.ok(ApiResponse.created(null));
    }

    @Operation(summary = "특정 아티클 조회",
            description = "아티클id로 특정 아티클을 조회합니다.")
    @GetMapping("{articleId}")
    public ResponseEntity<ApiResponse<ArticleRes>> getArticle(@PathVariable Long articleId) {
        ArticleRes res = articleService.getArticle(articleId);

        return ResponseEntity.ok(ApiResponse.ok(res));
    }

    @Operation(summary = "모든 아티클 조회",
            description = "모든 아티클을 조회합니다.")
    @GetMapping
    public ResponseEntity<ApiResponse<AllArticleRes>> getAllArticles() {
        AllArticleRes res = articleService.getAllArticle();

        return ResponseEntity.ok(ApiResponse.ok(res));
    }
}
