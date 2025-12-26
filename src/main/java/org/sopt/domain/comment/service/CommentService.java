package org.sopt.domain.comment.service;

import org.sopt.domain.comment.dto.req.CreateArticleReq;
import org.sopt.domain.comment.dto.res.AllArticleRes;
import org.sopt.domain.comment.dto.res.ArticleRes;

public interface CommentService {
    void createArticle(Long userId, CreateArticleReq req);

    ArticleRes getArticle(Long articleId);

    AllArticleRes getAllArticle();
}
