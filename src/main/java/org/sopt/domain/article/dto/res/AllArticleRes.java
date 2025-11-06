package org.sopt.domain.article.dto.res;

import org.sopt.domain.article.entity.Article;

import java.util.List;

public record AllArticleRes(
        List<ArticleRes> articles
) {
    public static AllArticleRes from(List<Article> articleList) {
        List<ArticleRes> resList = articleList.stream()
                .map(ArticleRes::from)
                .toList();

        return new AllArticleRes(resList);
    }
}
