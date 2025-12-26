package org.sopt.domain.article.dto.res;

import org.sopt.domain.article.entity.Article;
import org.sopt.domain.article.entity.ArticleTag;
import org.sopt.domain.comment.dto.res.CommentResponse;

import java.time.LocalDateTime;
import java.util.List;

public record ArticleRes(
        Long id,
        String title,
        String content,
        ArticleTag articleTag,
        LocalDateTime createdAt,
        Long memberId,
        List<CommentResponse> comments
) {
    public static ArticleRes from(Article article, List<CommentResponse> comments) {
        return new ArticleRes(
                article.getId(),
                article.getTitle(),
                article.getContent(),
                article.getArticleTag(),
                article.getCreatedAt(),
                article.getMember().getId(),
                comments
        );
    }
}
