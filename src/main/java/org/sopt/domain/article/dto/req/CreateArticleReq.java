package org.sopt.domain.article.dto.req;

import org.sopt.domain.article.entity.ArticleTag;

public record CreateArticleReq(
        String title,
        String content,
        ArticleTag articleTag
) {
}
