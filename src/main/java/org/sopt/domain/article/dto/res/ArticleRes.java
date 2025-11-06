package org.sopt.domain.article.dto.res;

import org.sopt.domain.article.entity.Article;
import org.sopt.domain.article.entity.ArticleTag;

import java.time.LocalDateTime;

public record ArticleRes(
        Long id,
        String title,
        String content,
        ArticleTag articleTag,
        LocalDateTime createdAt,
        Long memberId
) {
    public static ArticleRes from(Article article) {
        return new ArticleRes(
                article.getId(),
                article.getTitle(),
                article.getContent(),
                article.getArticleTag(),
                article.getCreatedAt(),
                article.getMember().getId()
        );
    }
}
