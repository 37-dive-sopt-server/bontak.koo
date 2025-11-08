package org.sopt.domain.article.service;

import org.sopt.domain.article.dto.req.CreateArticleReq;
import org.sopt.domain.article.dto.res.AllArticleRes;
import org.sopt.domain.article.dto.res.ArticleRes;

public interface ArticleService {
    void createArticle(Long userId, CreateArticleReq req);

    ArticleRes getArticle(Long articleId);

    AllArticleRes getAllArticle();
}
