package org.sopt.domain.article.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.sopt.domain.article.entity.Article;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.sopt.domain.article.entity.QArticle.article;
import static org.sopt.domain.member.entity.QMember.member;


@Repository
@RequiredArgsConstructor
public class ArticleCustomRepositoryImpl implements ArticleCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Article> findAllWithMember() {
        return queryFactory
                .selectFrom(article)
                .join(article.member, member).fetchJoin()
                .fetch();
    }
}

