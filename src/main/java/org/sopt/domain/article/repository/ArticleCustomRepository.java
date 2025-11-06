package org.sopt.domain.article.repository;

import org.sopt.domain.article.entity.Article;

import java.util.List;

public interface ArticleCustomRepository {
    List<Article> findAllWithMember();
}
